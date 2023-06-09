package com.stage.gestiondestock_backend.service;

import com.stage.gestiondestock_backend.service.criteria.CategoryCriteria;
import com.stage.gestiondestock_backend.dto.CategoryDto;

import java.util.List;

public interface CategoryService {

    CategoryDto save(CategoryDto dto);

    CategoryDto findById(Long id);

    CategoryDto findByCode(String code);

    List<CategoryDto> findAll();

    List<CategoryDto> listingCategory(CategoryCriteria categoryCriteria);

    void delete(Long id);
}
