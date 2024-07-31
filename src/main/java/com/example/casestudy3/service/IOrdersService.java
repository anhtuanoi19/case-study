package com.example.casestudy3.service;

import com.example.casestudy3.dto.request.OrdersDto;
import com.example.casestudy3.dto.response.ApiResponse;

import java.util.List;
import java.util.UUID;

public interface IOrdersService {
    public void createOrders() throws Exception;

    ApiResponse<OrdersDto> create(OrdersDto ordersDto);

    ApiResponse<OrdersDto> update(OrdersDto ordersDto, UUID id);

    ApiResponse<List<OrdersDto>> getAll();

    ApiResponse<OrdersDto> findById(UUID id);

    ApiResponse<Boolean> delete(UUID id);
}
