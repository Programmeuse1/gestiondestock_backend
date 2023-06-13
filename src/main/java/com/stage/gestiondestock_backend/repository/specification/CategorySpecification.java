package com.stage.gestiondestock_backend.repository.specification;

import com.stage.gestiondestock_backend.model.Category;
import com.stage.gestiondestock_backend.service.criteria.CategoryCriteria;
import org.springframework.data.jpa.domain.Specification;

import java.util.Objects;

public class CategorySpecification {

    private static Specification<Category> withDesignation(String designation){
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("designation"),"%".concat(designation).concat("%"));
    }

    private static Specification<Category> withActif(Boolean actif){
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("actif"),actif);
    }

    private static Specification<Category> withCode(String code){
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("code"),"%".concat(code).concat("%"));
    }

    public static Specification<Category> getCategory(CategoryCriteria categoryCriteria){

        Specification<Category> categorySpecification = (root, query, criteriaBuilder) -> null;

        if (categoryCriteria.getDesignation() != null){
            categorySpecification = Objects.requireNonNull(Specification.where(categorySpecification)).and(withDesignation(categoryCriteria.getDesignation()));
        }

        if (categoryCriteria.getCode() != null){
            categorySpecification = Objects.requireNonNull(Specification.where(categorySpecification)).and(withCode(categoryCriteria.getCode()));
        }

        if (Boolean.TRUE.equals(categoryCriteria.getActif()) || Boolean.FALSE.equals(categoryCriteria.getActif())){
            categorySpecification = Objects.requireNonNull(Specification.where(categorySpecification)).and(withActif(categoryCriteria.getActif()));
        }

        return categorySpecification;
    }
}
