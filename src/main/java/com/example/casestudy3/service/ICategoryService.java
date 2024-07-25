package com.example.casestudy3.service;

import com.example.casestudy3.dto.request.CategoryDto;
import org.springframework.data.domain.Page;

import java.util.UUID;

public interface ICategoryService {
    CategoryDto save(CategoryDto categoryDto);

    Page<CategoryDto> getAll(int page, int size);

    CategoryDto update(CategoryDto categoryDto);

    void delete(UUID id);
}
