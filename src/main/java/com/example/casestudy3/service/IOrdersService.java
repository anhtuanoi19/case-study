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
    ApiResponse<OrdersDto> update(OrdersDto ordersDto, UUID id);
    ApiResponse<List<OrdersDto>> getAll();
    ApiResponse<OrdersDto> findById(UUID id);
    ApiResponse<Boolean> delete(UUID id);
    ApiResponse<OrdersDto> createOrder(OrdersDto orderDto, UUID customerId, UUID deliveryId);
    ApiResponse<OrdersDto> createOrderNew(OrdersDto orderDto);
    ApiResponse<OrdersDto> updateOrderNew(UUID id, OrdersDto ordersDto);
    ApiResponse<OrdersDto> createOrderNew2(OrdersDto ordersDto);
}
