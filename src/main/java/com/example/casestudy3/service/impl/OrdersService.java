package com.example.casestudy3.service.impl;

import com.example.casestudy3.dto.request.OrdersDto;
import com.example.casestudy3.dto.response.ApiResponse;
import com.example.casestudy3.entity.Orders;
import com.example.casestudy3.service.IOrdersService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class OrdersService implements IOrdersService {

    @Override
    public ApiResponse<OrdersDto> create(OrdersDto ordersDto) {
        return null;
    }

    @Override
    public ApiResponse<OrdersDto> update(OrdersDto ordersDto, Long id) {
        return null;
    }

    @Override
    public ApiResponse<List<OrdersDto>> getAll() {
        return null;
    }

    @Override
    public ApiResponse<OrdersDto> findById(Long id) {
        return null;
    }

    @Override
    public ApiResponse<Boolean> delete(Long id) {
        return null;
    }
}
