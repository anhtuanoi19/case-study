package com.example.casestudy3.service.impl;

import com.example.casestudy3.CategoryMapper;
import com.example.casestudy3.dto.request.CategoryDto;
import com.example.casestudy3.entity.Categories;
import com.example.casestudy3.repository.CategoryRepository;
import com.example.casestudy3.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class CategoryService implements ICategoryService {
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    CategoryMapper categoryMapper;

    @Override
    public CategoryDto save(CategoryDto categoryDto) {
        Categories categories = categoryMapper.toCategory(categoryDto);
        categories = categoryRepository.save(categories);
        return categoryMapper.toCategoryDTO(categories);
    }

    @Override
    public Page<CategoryDto> getAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Categories> categories = categoryRepository.findAll(pageable);
        return categories.map(categoryMapper::toCategoryDTO);
    }

    @Override
    public CategoryDto update(CategoryDto categoryDto) {
        if (categoryDto.getId() == null) {
            throw new IllegalArgumentException("ID cua CategoryDto truyen vao null");
        }
        Optional<Categories> categoryOptional = categoryRepository.findById(categoryDto.getId());
        if (categoryOptional.isEmpty()) {
            throw new IllegalArgumentException("ID không tồn tại trong cơ sở dữ liệu: ");
        }
        Categories categories = categoryOptional.get();
        categories.setId(categoryDto.getId());
        categories.setName(categoryDto.getName());
        categories.setStatus(categoryDto.getStatus());
        categories.setCode(categoryDto.getCode());

        categories = categoryRepository.save(categories);
        return categoryMapper.toCategoryDTO(categories);
    }
    @Override
    public void delete(UUID id) {
        categoryRepository.deleteById(id);
    }
}
