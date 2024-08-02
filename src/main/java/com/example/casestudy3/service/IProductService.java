package com.example.casestudy3.service;

import com.example.casestudy3.dto.request.OrdersDto;
import com.example.casestudy3.dto.request.ProductDto;
import com.example.casestudy3.dto.response.ApiResponse;
import com.example.casestudy3.entity.Categories;
import com.example.casestudy3.entity.Orders;
import com.example.casestudy3.entity.Product;

import java.util.List;
import java.util.UUID;

public interface IProductService {
    ApiResponse<ProductDto> create(ProductDto productDto);
    ApiResponse<ProductDto> update(ProductDto productDto, UUID id);
    ApiResponse<List<ProductDto>> getAllProducts();
    ApiResponse<ProductDto> findById(UUID id);
    ApiResponse<Boolean> delete(UUID id);
}
