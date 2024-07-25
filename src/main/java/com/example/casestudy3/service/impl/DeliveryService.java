package com.example.casestudy3.service.impl;

import com.example.casestudy3.dto.request.DeliveryDto;
import com.example.casestudy3.dto.response.ApiResponse;
import com.example.casestudy3.entity.Delivery;
import com.example.casestudy3.repository.DeliveryRepository;
import com.example.casestudy3.service.IDeliveryService;
import com.example.casestudy3.tranferDatas.DeliveryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class DeliveryService implements IDeliveryService {

    @Autowired
    private DeliveryRepository deliveryRepository;

    @Override
    public ApiResponse<DeliveryDto> create(DeliveryDto deliveryDto) {
        Delivery delivery = DeliveryMapper.INSTANCE.toEntity(deliveryDto);
        delivery = deliveryRepository.save(delivery);
        DeliveryDto result = DeliveryMapper.INSTANCE.toDto(delivery);
        ApiResponse<DeliveryDto> apiResponse = new ApiResponse<>();
        apiResponse.setResult(result);
        return apiResponse;
    }

    @Override
    public ApiResponse<DeliveryDto> update(DeliveryDto deliveryDto, UUID id) {
        Delivery existingDelivery = deliveryRepository.findById(id).orElse(null);
        if (existingDelivery == null) {
            ApiResponse<DeliveryDto> apiResponse = new ApiResponse<>();
            apiResponse.setMessage("Delivery not found");
            return apiResponse;
        }
        Delivery delivery = DeliveryMapper.INSTANCE.toEntity(deliveryDto);
        delivery.setId(id);
        delivery = deliveryRepository.save(delivery);
        DeliveryDto result = DeliveryMapper.INSTANCE.toDto(delivery);
        ApiResponse<DeliveryDto> apiResponse = new ApiResponse<>();
        apiResponse.setResult(result);
        return apiResponse;
    }

    @Override
    public ApiResponse<List<DeliveryDto>> getAll() {
        List<Delivery> deliveryList = deliveryRepository.findAll();
        List<DeliveryDto> deliveryDtoList = DeliveryMapper.INSTANCE.toDtoList(deliveryList);
        ApiResponse<List<DeliveryDto>> apiResponse = new ApiResponse<>();
        apiResponse.setResult(deliveryDtoList);
        return apiResponse;
    }

    @Override
    public ApiResponse<DeliveryDto> findById(UUID id) {
        Delivery delivery = deliveryRepository.findById(id).orElse(null);
        if (delivery == null) {
            ApiResponse<DeliveryDto> apiResponse = new ApiResponse<>();
            apiResponse.setMessage("Delivery not found");
            return apiResponse;
        }
        DeliveryDto result = DeliveryMapper.INSTANCE.toDto(delivery);
        ApiResponse<DeliveryDto> apiResponse = new ApiResponse<>();
        apiResponse.setResult(result);
        return apiResponse;
    }

    @Override
    public ApiResponse<Boolean> delete(UUID id) {
        if (!deliveryRepository.existsById(id)) {
            ApiResponse<Boolean> apiResponse = new ApiResponse<>();
            apiResponse.setMessage("Delivery not found");
            apiResponse.setResult(false);
            return apiResponse;
        }
        deliveryRepository.deleteById(id);
        ApiResponse<Boolean> apiResponse = new ApiResponse<>();
        apiResponse.setResult(true);
        return apiResponse;
    }

}
