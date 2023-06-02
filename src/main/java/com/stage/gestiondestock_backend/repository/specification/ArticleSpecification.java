package com.stage.gestiondestock_backend.repository.specification;

import com.stage.gestiondestock_backend.model.Article;
import com.stage.gestiondestock_backend.service.criteria.ArticleCriteria;
import org.springframework.data.jpa.domain.Specification;

import java.util.Objects;

public class ArticleSpecification {

    private static Specification<Article> withDesignation(String designation){
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("designation"),"%".concat(designation).concat("%"));
    }

    private static Specification<Article> withActif(Boolean actif){
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("actif"),actif);
    }

    private static Specification<Article> withCode(String code){
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("code"),"%".concat(code).concat("%"));
    }

    public static Specification<Article> getArticle(ArticleCriteria articleCriteria){

        Specification<Article> articleSpecification = (root, query, criteriaBuilder) -> null;

        if (articleCriteria.getDesignation() != null){
            articleSpecification = Objects.requireNonNull(Specification.where(articleSpecification)).and(withDesignation(articleCriteria.getDesignation()));
        }

        if (articleCriteria.getCode() != null){
            articleSpecification = Objects.requireNonNull(Specification.where(articleSpecification)).and(withCode(articleCriteria.getCode()));
        }

        if (Boolean.TRUE.equals(articleCriteria.getActif()) || Boolean.FALSE.equals(articleCriteria.getActif())){
            articleSpecification = Objects.requireNonNull(Specification.where(articleSpecification)).and(withActif(articleCriteria.getActif()));
        }

        return articleSpecification;
    }

}
