package com.stage.gestiondestock_backend.repository.specification;

import com.stage.gestiondestock_backend.model.Article;
import com.stage.gestiondestock_backend.model.MvtStock;
import com.stage.gestiondestock_backend.service.criteria.MvtStockCriteria;
import org.springframework.data.jpa.domain.Specification;

import java.util.Objects;

public class MvtStockSpecification {

    private static Specification<MvtStock> withDateEnregistrement(String dateEnregistrement){
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("dateEnregistrement"),"%".concat(dateEnregistrement).concat("%"));
    }

    private static Specification<MvtStock> withArticle(Article article){
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("article"),article);
    }


    public static Specification<MvtStock> getMvtStock(MvtStockCriteria mvtStockCriteria){

        Specification<MvtStock> mvtStockSpecification = (root, query, criteriaBuilder) -> null;

        if (mvtStockCriteria.getArticle() != null){
            mvtStockSpecification = Objects.requireNonNull(Specification.where(mvtStockSpecification)).and(withArticle(mvtStockCriteria.getArticle()));
        }

        if (mvtStockCriteria.getDateEnregistrement() != null){
            mvtStockSpecification = Objects.requireNonNull(Specification.where(mvtStockSpecification)).and(withDateEnregistrement(mvtStockCriteria.getDateEnregistrement()));
        }

        return mvtStockSpecification;
    }
}
