package com.stage.gestiondestock_backend.service;

import com.stage.gestiondestock_backend.Dto.ArticleDto;

import java.util.List;

public interface ArticleService {

    ArticleDto save(ArticleDto dto);

    ArticleDto findById(Long id);

    ArticleDto findByCode(String code);

    List<ArticleDto> findAll();

    void delete(Long id);

}
