package com.stage.gestiondestock_backend.service;

import com.stage.gestiondestock_backend.dto.ArticleDto;
import com.stage.gestiondestock_backend.service.criteria.ArticleCriteria;

import java.util.List;

public interface ArticleService {

    ArticleDto save(ArticleDto dto);

    ArticleDto findById(Long id);

    ArticleDto findByCode(String code);

    List<ArticleDto> findAll();

    List<ArticleDto> listingArticle(ArticleCriteria articleCriteria);

    void delete(Long id);

}
