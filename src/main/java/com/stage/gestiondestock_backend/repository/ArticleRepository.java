package com.stage.gestiondestock_backend.repository;

import com.stage.gestiondestock_backend.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface ArticleRepository extends JpaRepository<Article, Long> {
        Optional<Article> findArticleByCode(String code);
}
