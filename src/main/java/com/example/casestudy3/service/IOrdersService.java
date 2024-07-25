package com.example.casestudy3.service;

import com.example.casestudy3.dto.request.DeliveryDto;
import com.example.casestudy3.dto.request.OrdersDto;
import com.example.casestudy3.dto.response.ApiResponse;
import com.example.casestudy3.entity.Categories;
import com.example.casestudy3.entity.Delivery;
import com.example.casestudy3.entity.Orders;

import java.util.List;
import java.util.UUID;

public interface IOrdersService {
    ApiResponse<OrdersDto> create(OrdersDto ordersDto);
    ApiResponse<OrdersDto> update(OrdersDto ordersDto, Long id);
    ApiResponse<List<OrdersDto>> getAll();
    ApiResponse<OrdersDto> findById(Long id);
    ApiResponse<Boolean> delete(Long id);
}
