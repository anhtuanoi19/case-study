package com.example.casestudy3.controller;

import com.example.casestudy3.dto.request.CategoryDto;
import com.example.casestudy3.dto.response.ApiResponse;
import com.example.casestudy3.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
    @Autowired
    private ICategoryService categoryService;

    @PostMapping
    public ResponseEntity<ApiResponse<CategoryDto>> createCategory(@RequestBody CategoryDto categoryDto) {
        ApiResponse<CategoryDto> response = categoryService.create(categoryDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<CategoryDto>> updateCategory(@PathVariable UUID id, @RequestBody CategoryDto categoryDto) {
        ApiResponse<CategoryDto> response = categoryService.update(categoryDto, id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<CategoryDto>>> getAllCategories() {
        ApiResponse<List<CategoryDto>> response = categoryService.getAll();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<CategoryDto>> getCategoryById(@PathVariable UUID id) {
        ApiResponse<CategoryDto> response = categoryService.findById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Boolean>> deleteCategory(@PathVariable UUID id) {
        ApiResponse<Boolean> response = categoryService.delete(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
