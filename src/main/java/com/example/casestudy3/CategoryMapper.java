package com.example.casestudy3;

import com.example.casestudy3.dto.request.CategoryDto;
import com.example.casestudy3.entity.Categories;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    CategoryDto toCategoryDTO(Categories categories);
    List<CategoryDto> toCategoryDTOList(List<Categories> lstCategory);

    Categories toCategory(CategoryDto categoryDto);
    List<Categories> toCategoryList(List<CategoryDto> lstCategoryDTO);
}
