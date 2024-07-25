package com.example.casestudy3.service;

import com.example.casestudy3.dto.request.CustomerDto;
import com.example.casestudy3.dto.request.DeliveryDto;
import com.example.casestudy3.dto.response.ApiResponse;
import com.example.casestudy3.entity.Categories;
import com.example.casestudy3.entity.Customer;
import com.example.casestudy3.entity.Delivery;

import java.util.List;
import java.util.UUID;

public interface ICustomerService {
    ApiResponse<CustomerDto> create(CustomerDto customerDto);
    ApiResponse<CustomerDto> update(CustomerDto customerDto, UUID id);
    ApiResponse<List<CustomerDto>> getAll();
    ApiResponse<CustomerDto> findById(UUID id);
    ApiResponse<Boolean> delete(UUID id);
}
