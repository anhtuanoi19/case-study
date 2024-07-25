package com.example.casestudy3.service.impl;

import com.example.casestudy3.dto.request.CustomerDto;
import com.example.casestudy3.dto.response.ApiResponse;
import com.example.casestudy3.entity.Customer;
import com.example.casestudy3.service.ICustomerService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CustomerService implements ICustomerService {


    @Override
    public ApiResponse<CustomerDto> create(CustomerDto customerDto) {
        return null;
    }

    @Override
    public ApiResponse<CustomerDto> update(CustomerDto customerDto, UUID id) {
        return null;
    }

    @Override
    public ApiResponse<List<CustomerDto>> getAll() {
        return null;
    }

    @Override
    public ApiResponse<CustomerDto> findById(UUID id) {
        return null;
    }

    @Override
    public ApiResponse<Boolean> delete(UUID id) {
        return null;
    }
}
