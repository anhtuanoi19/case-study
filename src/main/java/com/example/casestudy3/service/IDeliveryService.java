package com.example.casestudy3.service;

import com.example.casestudy3.dto.request.DeliveryDto;
import com.example.casestudy3.dto.response.ApiResponse;
import com.example.casestudy3.entity.Categories;
import com.example.casestudy3.entity.Delivery;

import java.util.List;
import java.util.UUID;

public interface IDeliveryService {
    ApiResponse<DeliveryDto> create(DeliveryDto deliveryDto);
    ApiResponse<DeliveryDto> update(DeliveryDto deliveryDto, UUID id);
    ApiResponse<List<DeliveryDto>> getAll();
    ApiResponse<DeliveryDto> findById(UUID id);
    ApiResponse<Boolean> delete(UUID id);
}
