package com.example.casestudy3.tranferDatas;

import com.example.casestudy3.dto.request.CategoryDto;
import com.example.casestudy3.entity.Categories;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CategoryMapper {
    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    @Mapping(source = "name", target = "name")
    @Mapping(source = "code", target = "code")
    @Mapping(source = "status", target = "status")
    CategoryDto toDto(Categories entity);

    @Mapping(source = "name", target = "name")
    @Mapping(source = "code", target = "code")
    @Mapping(source = "status", target = "status")
    Categories toEntity(CategoryDto dto);


    List<CategoryDto> toDtoList(List<Categories> categories);

    List<Categories> toEntityList(List<CategoryDto> categoryDtos);
}
