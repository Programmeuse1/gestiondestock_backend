package com.stage.gestiondestock_backend.controller;

import com.stage.gestiondestock_backend.Dto.CategoryDto;
import com.stage.gestiondestock_backend.controller.api.CategoryApi;
import com.stage.gestiondestock_backend.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CategoryController  implements CategoryApi {

    private CategoryService categoryService;
    @Autowired
    public CategoryController(
            CategoryService categoryService
    ) {
        this.categoryService = categoryService;
    }

    @Override
    public CategoryDto save(CategoryDto dto) { return categoryService.save(dto); }

    @Override
    public CategoryDto findById(Long id) {
        return categoryService.findById(id);
    }

    @Override
    public CategoryDto findByCode(String code) { return categoryService.findByCode(code); }

    @Override
    public List<CategoryDto> findAll() {
        return categoryService.findAll();
    }

    @Override
    public void delete(Long id) {
       categoryService.delete(id);

    }
}
