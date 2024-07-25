package com.example.casestudy3.service.impl;

import com.example.casestudy3.dto.request.OrdersDto;
import com.example.casestudy3.dto.response.ApiResponse;
import com.example.casestudy3.entity.Orders;
import com.example.casestudy3.repository.OrdersRepository;
import com.example.casestudy3.service.IOrdersService;
import com.example.casestudy3.tranferDatas.OrdersMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class OrdersService implements IOrdersService {
    @Autowired
    private OrdersRepository ordersRepository;

    @Override
    public ApiResponse<OrdersDto> create(OrdersDto ordersDto) {
        Orders orders = OrdersMapper.INSTANCE.toEntity(ordersDto);
        orders = ordersRepository.save(orders);
        OrdersDto result = OrdersMapper.INSTANCE.toDto(orders);
        ApiResponse<OrdersDto> apiResponse = new ApiResponse<>();
        apiResponse.setResult(result);
        return apiResponse;
    }

    @Override
    public ApiResponse<OrdersDto> update(OrdersDto ordersDto, UUID id) {
        Orders existingOrders = ordersRepository.findById(id).orElse(null);
        if (existingOrders == null) {
            ApiResponse<OrdersDto> apiResponse = new ApiResponse<>();
            apiResponse.setMessage("Order not found");
            return apiResponse;
        }
        Orders orders = OrdersMapper.INSTANCE.toEntity(ordersDto);
        orders.setId(id);
        orders = ordersRepository.save(orders);
        OrdersDto result = OrdersMapper.INSTANCE.toDto(orders);
        ApiResponse<OrdersDto> apiResponse = new ApiResponse<>();
        apiResponse.setResult(result);
        return apiResponse;
    }

    @Override
    public ApiResponse<List<OrdersDto>> getAll() {
        List<Orders> ordersList = ordersRepository.findAll();
        List<OrdersDto> ordersDtoList = OrdersMapper.INSTANCE.toDtoList(ordersList);
        ApiResponse<List<OrdersDto>> apiResponse = new ApiResponse<>();
        apiResponse.setResult(ordersDtoList);
        return apiResponse;
    }

    @Override
    public ApiResponse<OrdersDto> findById(UUID id) {
        Orders orders = ordersRepository.findById(id).orElse(null);
        if (orders == null) {
            ApiResponse<OrdersDto> apiResponse = new ApiResponse<>();
            apiResponse.setMessage("Order not found");
            return apiResponse;
        }
        OrdersDto result = OrdersMapper.INSTANCE.toDto(orders);
        ApiResponse<OrdersDto> apiResponse = new ApiResponse<>();
        apiResponse.setResult(result);
        return apiResponse;
    }

    @Override
    public ApiResponse<Boolean> delete(UUID id) {
        if (!ordersRepository.existsById(id)) {
            ApiResponse<Boolean> apiResponse = new ApiResponse<>();
            apiResponse.setMessage("Order not found");
            apiResponse.setResult(false);
            return apiResponse;
        }
        ordersRepository.deleteById(id);
        ApiResponse<Boolean> apiResponse = new ApiResponse<>();
        apiResponse.setResult(true);
        return apiResponse;
    }
}
