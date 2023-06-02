package com.stage.gestiondestock_backend.service.implement;

import com.google.common.base.Strings;
import com.stage.gestiondestock_backend.Dto.ArticleDto;
import com.stage.gestiondestock_backend.Dto.CategoryDto;
import com.stage.gestiondestock_backend.Validator.ArticleValidator;
import com.stage.gestiondestock_backend.exception.EntityNotFoundException;
import com.stage.gestiondestock_backend.exception.ErrorCodes;
import com.stage.gestiondestock_backend.exception.InvalidEntityException;
import com.stage.gestiondestock_backend.model.Article;
import com.stage.gestiondestock_backend.model.Category;
import com.stage.gestiondestock_backend.repository.ArticleRepository;
import com.stage.gestiondestock_backend.repository.CategoryRepository;
import com.stage.gestiondestock_backend.repository.specification.ArticleSpecification;
import com.stage.gestiondestock_backend.service.ArticleService;
import com.stage.gestiondestock_backend.service.criteria.ArticleCriteria;
import com.stage.gestiondestock_backend.utils.MethodUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor
public class ArticleServiceImplement implements ArticleService {

    private final ArticleRepository articleRepository;
    private final CategoryRepository categoryRepository;


    @Override
    public ArticleDto save(ArticleDto dto) {
        List<String> errors = ArticleValidator.validate(dto);
        if(!errors.isEmpty()){
            log.error("Article is not valid{}", dto);
            throw new InvalidEntityException("L'article n'est pas valide", ErrorCodes.ARTICLE_NOT_VALID, errors);
        }
        if (dto.getCategory().getId() != null && categoryRepository.findById(dto.getCategory().getId()).isPresent()) {
            dto.setCategory(CategoryDto.fromEntity(categoryRepository.findById(dto.getCategory().getId()).get()));
        } else {
            throw new EntityNotFoundException("la categorie n'existe pas");
        }
        Article article1 = articleRepository.save(ArticleDto.toEntity(dto));
        article1.setCode(article1.getCode() == null ? "ART-" + MethodUtils.format(article1.getId().intValue(), 6) : article1.getCode());
        return ArticleDto.fromEntity(articleRepository.save(article1));
    }

    @Override
    public ArticleDto findById(Long id) {
        if (id == null){
            log.error("Article ID is null");
            return null;
        }
        Optional<Article>article = articleRepository.findById(id);

        return Optional.of(ArticleDto.fromEntity(article.get())).orElseThrow(()->
                new EntityNotFoundException(
                        "Aucun article avec l'ID = "+ id + " n' ete trouve dans la bd",
                        ErrorCodes.ARTICLE_NOT_FOUND)
        );
    }

    @Override
    public ArticleDto findByCode(String code) {
        if (!StringUtils.hasLength(code)) {
            log.error("Article CODE is null");
            return null;
        }
        Optional<Article> article = articleRepository.findArticleByCode(code);

        return Optional.of(ArticleDto.fromEntity(article.get())).orElseThrow(()->
                new EntityNotFoundException(
                        "Aucun article avec le CODE = "+ code + " n' ete trouve dans la bd",
                        ErrorCodes.ARTICLE_NOT_FOUND)
        );
    }

    @Override
    public List<ArticleDto> findAll() {
        return articleRepository.findAll().stream()
                .map(ArticleDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<ArticleDto> listingArticle(ArticleCriteria articleCriteria) {
        System.out.println("\n\narticleCriteria: "+articleCriteria+"\n");

        return articleRepository.findAll(ArticleSpecification.getArticle(articleCriteria),
                Strings.isNullOrEmpty(articleCriteria.getNombreDeResultat()) ? Pageable.unpaged() :
                        PageRequest.of(0, Integer.parseInt(articleCriteria.getNombreDeResultat()))).getContent().stream()
                .map(ArticleDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        if (id == null) {
            log.error("Article ID is null");
            return;
        }
        articleRepository.deleteById(id);

    }

}
