package com.stage.gestiondestock_backend.service.implement;

import com.stage.gestiondestock_backend.Dto.CategoryDto;
import com.stage.gestiondestock_backend.Validator.CategoryValidator;
import com.stage.gestiondestock_backend.exception.EntityNotFoundException;
import com.stage.gestiondestock_backend.exception.ErrorCodes;
import com.stage.gestiondestock_backend.exception.InvalidEntityException;
import com.stage.gestiondestock_backend.model.Category;
import com.stage.gestiondestock_backend.repository.CategoryRepository;
import com.stage.gestiondestock_backend.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CategoryServiceImplement implements CategoryService {

    private CategoryRepository categoryRepository;
    @Autowired
    public CategoryServiceImplement(
            CategoryRepository categoryRepository
    ){
        this.categoryRepository= categoryRepository;
    }

    @Override
    public CategoryDto save(CategoryDto dto) {
        System.out.println("\ncategory dto: "+dto.toString()+"\n");
        List<String> errors = CategoryValidator.validate(dto);
        if(!errors.isEmpty()){
            log.error("Category is not valid{}", dto);
            throw new InvalidEntityException("La category n'est pas valide", ErrorCodes.CATEGORY_NOT_VALID, errors);
        }
        return CategoryDto.builder().build().fromEntity(
                categoryRepository.save(
                        CategoryDto.toEntity(dto)
                )
        );
    }

    @Override
    public CategoryDto findById(Long id) {
        if (id == null){
            log.error("Category ID is null");
            return null;
        }
        Optional<Category>category = categoryRepository.findById(id);

        return Optional.of(CategoryDto.fromEntity(category.get())).orElseThrow(()->
                new EntityNotFoundException(
                        "Aucune category avec l'ID = "+ id + " n' ete trouve dans la bd",
                        ErrorCodes.CATEGORY_NOT_FOUND)
        );
    }

    @Override
    public CategoryDto findByCode(String code) {
        if (!StringUtils.hasLength(code)) {
            log.error("Category CODE is null");
            return null;
        }
        Optional<Category> article = categoryRepository.findCategoryByCode(code);

        return Optional.of(CategoryDto.fromEntity(article.get())).orElseThrow(()->
                new EntityNotFoundException(
                        "Aucune category avec le CODE = "+ code + " n' ete trouve dans la bd",
                        ErrorCodes.CATEGORY_NOT_FOUND)
        );
    }

    @Override
    public List<CategoryDto> findAll() {
        return categoryRepository.findAll().stream()
                .map(CategoryDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        if (id == null) {
            log.error("Category ID is null");
            return;
        }
       categoryRepository.deleteById(id);


    }
}
