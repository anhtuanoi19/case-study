package com.example.casestudy3.controller;

import com.example.casestudy3.dto.request.CategoryDto;
import com.example.casestudy3.dto.response.ApiResponse;
import com.example.casestudy3.entity.Categories;
import com.example.casestudy3.exception.AppException;
import com.example.casestudy3.exception.ErrorCode;
import com.example.casestudy3.service.ICategoryService;
import com.example.casestudy3.service.IService;
import com.example.casestudy3.service.impl.CategoryService;
import com.example.casestudy3.tranferDatas.TranferDatas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    private ICategoryService iCategoryService;


    //namdp
    @PostMapping("/create")
    public ResponseEntity<CategoryDto> created(@RequestBody CategoryDto categoryDto) {
        return new ResponseEntity<>(iCategoryService.save(categoryDto), HttpStatus.OK);
    }
    @PostMapping("/put")
    public ResponseEntity<?> update(@RequestBody CategoryDto categoryDto) {
        return new ResponseEntity<>(iCategoryService.update(categoryDto), HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<Page<CategoryDto>> getAllProjects(@RequestParam(defaultValue = "0") int page,
                                                           @RequestParam(defaultValue = "10") int size) {
        Page<CategoryDto> categoryDtos = iCategoryService.getAll(page, size);
        return new ResponseEntity<>(categoryDtos, HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id) {
        iCategoryService.delete(id);
        return new ResponseEntity<>("Deleted successfull!", HttpStatus.OK);
    }





}
