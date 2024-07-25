package com.example.casestudy3.service.impl;

import com.example.casestudy3.dto.request.CategoryDto;
import com.example.casestudy3.dto.response.ApiResponse;
import com.example.casestudy3.entity.Categories;
import com.example.casestudy3.exception.AppException;
import com.example.casestudy3.exception.ErrorCode;
import com.example.casestudy3.repository.CategoryRepository;
import com.example.casestudy3.service.ICategoryService;
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

    @Override
    public ApiResponse<CategoryDto> create(CategoryDto categoryDto) {
        return null;
    }

    @Override
    public ApiResponse<CategoryDto> update(CategoryDto categoryDto, Long id) {
        return null;
    }

    @Override
    public ApiResponse<List<CategoryDto>> getAll() {
        return null;
    }

    @Override
    public ApiResponse<CategoryDto> findById(Long id) {
        return null;
    }

    @Override
    public ApiResponse<Boolean> delete(Long id) {
        return null;
    }
}
