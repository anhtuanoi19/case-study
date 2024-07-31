package com.example.casestudy3.service.impl;

import com.example.casestudy3.dto.request.CategoryDto;
import com.example.casestudy3.dto.response.ApiResponse;
import com.example.casestudy3.entity.Categories;
import com.example.casestudy3.entity.Log;
import com.example.casestudy3.exception.AppException;
import com.example.casestudy3.exception.ErrorCode;
import com.example.casestudy3.repository.CategoryRepository;
import com.example.casestudy3.repository.LogRepository;
import com.example.casestudy3.service.ICategoryService;
import com.example.casestudy3.tranferDatas.CategoryMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class CategoryService implements ICategoryService {

    @Autowired
    private CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper = CategoryMapper.INSTANCE;

    private final Logger logger = LoggerFactory.getLogger(CategoryService.class);

    @Autowired
    private LogRepository logRepository;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void saveLog(String message) {
        Log log = new Log();
        log.setMessage(message);
        logRepository.save(log);
    }


    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public ApiResponse<CategoryDto> create(CategoryDto categoryDto) {
        try {
//            if (categoryRepository.existsByCode(categoryDto.getCode())) {
//                throw new BackingStoreException("code khong trung nhau");
//            }
            Categories category = categoryMapper.toEntity(categoryDto);
            category = categoryRepository.save(category);
            CategoryDto result = categoryMapper.toDto(category);
            ApiResponse<CategoryDto> apiResponse = new ApiResponse<>();
            apiResponse.setResult(result);
            apiResponse.setMessage("Category created successfully");

            saveLog("Thành công rồi");

            return apiResponse;

        } catch (Error e) {
            saveLog("Có lỗi");
            throw new Error(e.getCause());
        }
    }

    @Override
    public ApiResponse<CategoryDto> update(CategoryDto categoryDto, UUID id) {
        if (!categoryRepository.existsById(id)) {
            throw new AppException(ErrorCode.CATEGORY_NOT_EXISTED);
        }
        Categories category = categoryMapper.toEntity(categoryDto);
        category.setId(id);
        category = categoryRepository.save(category);
        CategoryDto result = categoryMapper.toDto(category);
        ApiResponse<CategoryDto> apiResponse = new ApiResponse<>();
        apiResponse.setResult(result);
        apiResponse.setMessage("Category updated successfully");
        return apiResponse;
    }

    @Override
    public ApiResponse<List<CategoryDto>> getAll() {
        List<Categories> categories = categoryRepository.findAll();
        List<CategoryDto> categoryDtos = categoryMapper.toDtoList(categories);
        ApiResponse<List<CategoryDto>> apiResponse = new ApiResponse<>();
        apiResponse.setResult(categoryDtos);
        apiResponse.setMessage("Categories retrieved successfully");
        return apiResponse;
    }

    @Override
    public ApiResponse<CategoryDto> findById(UUID id) {
        Categories category = categoryRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.CATEGORY_NOT_EXISTED));
        CategoryDto categoryDto = categoryMapper.toDto(category);
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
