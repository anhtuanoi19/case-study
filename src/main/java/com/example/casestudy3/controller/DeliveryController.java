package com.example.casestudy3.controller;

import com.example.casestudy3.dto.request.DeliveryDto;
import com.example.casestudy3.dto.response.ApiResponse;
import com.example.casestudy3.entity.Delivery;
import com.example.casestudy3.service.IDeliveryService;
import com.example.casestudy3.service.impl.DeliveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/delivery")
public class DeliveryController {
    @Autowired
    private IDeliveryService deliveryService;

    @PostMapping("/create")
    public ApiResponse<DeliveryDto> createDelivery(@RequestBody DeliveryDto deliveryDto) {
        return deliveryService.create(deliveryDto);
    }

    @GetMapping("/all")
    public ApiResponse<List<DeliveryDto>> getAllDeliveries() {
        // Gọi phương thức getAll từ DeliveryService
        ApiResponse<List<DeliveryDto>> response = deliveryService.getAll();
        return response;
    }

}
