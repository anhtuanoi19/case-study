package com.example.casestudy3.service;

import com.example.casestudy3.dto.request.CategoryDto;
import com.example.casestudy3.dto.request.ProductDto;
import com.example.casestudy3.dto.response.ApiResponse;
import com.example.casestudy3.entity.Categories;
import com.example.casestudy3.entity.Product;
import jdk.jfr.Category;

import java.util.List;
import java.util.UUID;

public interface ICategoryService {
    ApiResponse<CategoryDto> create(CategoryDto categoryDto);
    ApiResponse<CategoryDto> update(CategoryDto categoryDto, UUID id);
    ApiResponse<List<CategoryDto>> getAll();
    ApiResponse<CategoryDto> findById(UUID id);
    ApiResponse<Boolean> delete(UUID id);
}
