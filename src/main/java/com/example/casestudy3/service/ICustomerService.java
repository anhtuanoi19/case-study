package com.example.casestudy3.service;

import com.example.casestudy3.dto.request.CustomerDto;
import com.example.casestudy3.dto.response.ApiResponse;
import com.example.casestudy3.entity.Customer;
import com.example.casestudy3.entity.Orders;

import java.util.List;
import java.util.UUID;

public interface ICustomerService {

    void createOrderByCustomer(UUID customer_id, Orders newOrders);

    Customer createCustomerWithOrder(Customer newCustomer);

    void testLazyLoading(UUID customerId);

    ApiResponse<CustomerDto> create(CustomerDto customerDto);

    ApiResponse<CustomerDto> update(CustomerDto customerDto, UUID id);

    ApiResponse<List<CustomerDto>> getAll();

    ApiResponse<CustomerDto> findById(UUID id);

    ApiResponse<Boolean> delete(UUID id);
}
