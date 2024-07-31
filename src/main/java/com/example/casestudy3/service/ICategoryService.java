package com.example.casestudy3.service;

import com.example.casestudy3.dto.request.CategoryDto;
import com.example.casestudy3.dto.response.ApiResponse;

import java.util.List;
import java.util.UUID;

public interface ICategoryService {
    ApiResponse<CategoryDto> create(CategoryDto categoryDto);

    ApiResponse<CategoryDto> update(CategoryDto categoryDto, UUID id);

    ApiResponse<List<CategoryDto>> getAll();

    ApiResponse<CategoryDto> findById(UUID id);

    ApiResponse<Boolean> delete(UUID id);
}
