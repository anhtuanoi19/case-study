package com.example.casestudy3.service.impl;

import com.example.casestudy3.dto.request.CategoryDto;
import com.example.casestudy3.dto.response.ApiResponse;
import com.example.casestudy3.entity.Categories;
import com.example.casestudy3.exception.AppException;
import com.example.casestudy3.exception.ErrorCode;
import com.example.casestudy3.repository.CategoryRepository;
import com.example.casestudy3.service.ICategoryService;
import com.example.casestudy3.tranferDatas.CategoryMapper;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Service
public class CategoryService implements ICategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public ApiResponse<CategoryDto> create(CategoryDto categoryDto) {
        Categories category = CategoryMapper.INSTANCE.toEntity(categoryDto);
        category = categoryRepository.save(category);
        CategoryDto result = CategoryMapper.INSTANCE.toDto(category);
        ApiResponse<CategoryDto> apiResponse = new ApiResponse<>();
        apiResponse.setResult(result);
        apiResponse.setMessage("Category created successfully");
        return apiResponse;
    }

    @Override
    public ApiResponse<CategoryDto> update(CategoryDto categoryDto, UUID id) {
        if (!categoryRepository.existsById(id)) {
            throw new AppException(ErrorCode.CATEGORY_NOT_EXISTED);
        }
        Categories category = CategoryMapper.INSTANCE.toEntity(categoryDto);
        category.setId(id);
        category = categoryRepository.save(category);
        CategoryDto result = CategoryMapper.INSTANCE.toDto(category);
        ApiResponse<CategoryDto> apiResponse = new ApiResponse<>();
        apiResponse.setResult(result);
        apiResponse.setMessage("Category updated successfully");
        return apiResponse;
    }

    @Override
    public ApiResponse<List<CategoryDto>> getAll() {
        List<Categories> categories = categoryRepository.findAll();
        List<CategoryDto> categoryDtos = CategoryMapper.INSTANCE.toDtoList(categories);
        ApiResponse<List<CategoryDto>> apiResponse = new ApiResponse<>();
        apiResponse.setResult(categoryDtos);
        apiResponse.setMessage("Categories retrieved successfully");
        return apiResponse;
    }

    @Override
    public ApiResponse<CategoryDto> findById(UUID id) {
        Categories category = categoryRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.CATEGORY_NOT_EXISTED));
        CategoryDto categoryDto = CategoryMapper.INSTANCE.toDto(category);
        ApiResponse<CategoryDto> apiResponse = new ApiResponse<>();
        apiResponse.setResult(categoryDto);
        apiResponse.setMessage("Category retrieved successfully");
        return apiResponse;
    }

    @Override
    public ApiResponse<Boolean> delete(UUID id) {
        if (!categoryRepository.existsById(id)) {
            throw new AppException(ErrorCode.CATEGORY_NOT_EXISTED);
        }
        categoryRepository.deleteById(id);
        ApiResponse<Boolean> apiResponse = new ApiResponse<>();
        apiResponse.setResult(true);
        apiResponse.setMessage("Category deleted successfully");
        return apiResponse;
    }
}
