package com.stage.gestiondestock_backend.service.implement;


import com.stage.gestiondestock_backend.dto.LigneVenteDto;
import com.stage.gestiondestock_backend.dto.VentesDto;
import com.stage.gestiondestock_backend.Validator.VentesValidator;
import com.stage.gestiondestock_backend.exception.EntityNotFoundException;
import com.stage.gestiondestock_backend.exception.ErrorCodes;
import com.stage.gestiondestock_backend.exception.InvalidEntityException;
import com.stage.gestiondestock_backend.model.Article;
import com.stage.gestiondestock_backend.model.LigneVente;
import com.stage.gestiondestock_backend.model.Ventes;
import com.stage.gestiondestock_backend.repository.ArticleRepository;
import com.stage.gestiondestock_backend.repository.LigneVenteRepository;
import com.stage.gestiondestock_backend.repository.VentesRepository;
import com.stage.gestiondestock_backend.service.VentesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class VentesServiceImplement implements VentesService {

    private ArticleRepository articleRepository;
    private VentesRepository ventesRepository;
    private LigneVenteRepository ligneVenteRepository;

    @Autowired
    public VentesServiceImplement(ArticleRepository articleRepository, VentesRepository ventesRepository, LigneVenteRepository ligneVenteRepository) {
        this.articleRepository = articleRepository;
        this.ventesRepository = ventesRepository;
        this.ligneVenteRepository = ligneVenteRepository;
    }

    @Override
    public VentesDto save(VentesDto dto) {
        List<String> errors = VentesValidator.validate(dto);
        if(!errors.isEmpty()){
            log.error("Vente n'est pas valide");
            throw new InvalidEntityException("L'objet vente n'est pas valide", ErrorCodes.VENTES_NOT_VALID, errors);
        }

        List<String> articleErrors = new ArrayList<>();

        dto.getLigneVentes().forEach(ligneVenteDto -> {
            Optional<Article> article = articleRepository.findById(ligneVenteDto.getArticle().getId());
            if(article.isEmpty()){
                articleErrors.add("Aucun article avec l'ID" + ligneVenteDto.getArticle().getId() + "n'a ete trouve dans la BD");
            }
        });

        if(!articleErrors.isEmpty()) {
            log.error("One or more articles were not foun in the BD,{}", errors);
            throw new InvalidEntityException("Un ou plusiers article n'ont pas ete trouve dans la BD", ErrorCodes.VENTES_NOT_VALID, errors);
        }

        Ventes savedVentes = ventesRepository.save(VentesDto.toEntity(dto));

        dto.getLigneVentes().forEach(ligneVenteDto -> {
            LigneVente ligneVente = LigneVenteDto.toEntity(ligneVenteDto);
            ligneVente.setVente((savedVentes));
            ligneVenteRepository.save((ligneVente));
        });
        return VentesDto.fromEntity(savedVentes);
    }

    @Override
    public VentesDto findById(Long id) {
        if(id==null) {
            log.error("Ventes ID is null");
            return null;
        }
        return ventesRepository.findById(id)
                .map(VentesDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException("Aucune vente n'a ete trouvedans la BD", ErrorCodes.VENTES_NOT_FOUND));
    }

    @Override
    public VentesDto findByCode(String code) {
        if (!StringUtils.hasLength(code)){
            log.error(("vente CODE is NULL"));
            return null;
        }
        return ventesRepository.findVentesByCode(code)
                .map(VentesDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Aucune vente client n'a ete trouve avec le CODE" + code, ErrorCodes.VENTES_NOT_FOUND
                ));

    }

    @Override
    public List<VentesDto> findAll() {
        return ventesRepository.findAll().stream()
                .map(VentesDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        if(id == null) {
            log.error("Vente ID is NULL");
            return;
        }
        ventesRepository.deleteById(id);
    }
}
