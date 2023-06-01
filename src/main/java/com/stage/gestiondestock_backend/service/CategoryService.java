package com.stage.gestiondestock_backend.service;

import com.stage.gestiondestock_backend.Dto.CategoryDto;

import java.util.List;

public interface CategoryService {

    CategoryDto save(CategoryDto dto);

    CategoryDto findById(Long id);

    CategoryDto findByCode(String code);

    List<CategoryDto> findAll();

    void delete(Long id);
}
