package com.stage.gestiondestock_backend.repository;

import com.stage.gestiondestock_backend.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findCategoryByCode(String code);
}
