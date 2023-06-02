package com.stage.gestiondestock_backend.repository;

import com.stage.gestiondestock_backend.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface ArticleRepository extends JpaRepository<Article, Long>, JpaSpecificationExecutor<Article> {
        Optional<Article> findArticleByCode(String code);
}
