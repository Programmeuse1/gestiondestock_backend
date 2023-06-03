package com.stage.gestiondestock_backend.controller;

import com.stage.gestiondestock_backend.Dto.ArticleDto;
import com.stage.gestiondestock_backend.controller.api.ArticleApi;
import com.stage.gestiondestock_backend.service.ArticleService;
import com.stage.gestiondestock_backend.service.criteria.ArticleCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ArticleController implements ArticleApi {

    private ArticleService articleService;

    @Autowired
    public ArticleController(
            ArticleService articleService
    ) {
        this.articleService = articleService;
    }

    @Override
    public ArticleDto save(ArticleDto dto) {
        return articleService.save(dto); }

    @Override
    public ArticleDto findById(Long id) {
        return articleService.findById(id);
    }

    @Override
    public ArticleDto findByCode(String code) {
        return articleService.findByCode(code);
    }

    @Override
    public List<ArticleDto> findAll() {
        return articleService.findAll();
    }

    @Override
    public void delete(Long id) {
       articleService.delete(id);
    }

    @Override
    public List<ArticleDto> listingArticle(ArticleCriteria articleCriteria) {
        return articleService.listingArticle(articleCriteria);
    }
}
