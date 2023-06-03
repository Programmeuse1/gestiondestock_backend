package com.stage.gestiondestock_backend.dto;

import com.stage.gestiondestock_backend.model.Article;
import com.stage.gestiondestock_backend.model.Category;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDto {

    private Long id;

    private String code;

    private boolean actif;

    private LocalDateTime dateEnregistrement;

    private String designation;

    private Integer idEntreprise;

    private List<Article> articles;

    public static CategoryDto fromEntity(Category category){
        if(category == null){
            return null;
        }

        return CategoryDto.builder()
                .id(category.getId())
                .dateEnregistrement(category.getDateEnregistrement())
                .actif(category.isActif())
                .code(category.getCode())
                .designation(category.getDesignation())
                .idEntreprise(category.getIdEntreprise())
                .build();
    }

    public static Category toEntity(CategoryDto categoryDto) {
        if ( categoryDto == null) {
            return null;
            //TODO throw exception
        }
       Category category = new Category();
                category.setId(categoryDto.getId());
                category.setDateEnregistrement(categoryDto.getDateEnregistrement());
                category.setActif(categoryDto.isActif());
                category.setCode(categoryDto.getCode());
                category.setDesignation(categoryDto.getDesignation());
                category.setIdEntreprise(categoryDto.getIdEntreprise());

                return category;
    }
}
