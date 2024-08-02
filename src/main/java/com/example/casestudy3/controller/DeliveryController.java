package com.example.casestudy3.controller;

import com.example.casestudy3.dto.request.DeliveryDto;
import com.example.casestudy3.dto.response.ApiResponse;
import com.example.casestudy3.entity.Delivery;
import com.example.casestudy3.service.IDeliveryService;
import com.example.casestudy3.service.impl.DeliveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/delivery")
public class DeliveryController {
    @Autowired
    private IDeliveryService deliveryService;
    @PostMapping
    public ResponseEntity<ApiResponse<DeliveryDto>> create(@RequestBody DeliveryDto deliveryDto) {
        ApiResponse<DeliveryDto> response = deliveryService.create(deliveryDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<DeliveryDto>> update(@PathVariable UUID id, @RequestBody DeliveryDto deliveryDto) {
        ApiResponse<DeliveryDto> response = deliveryService.update(deliveryDto, id);
        return response.getResult() == null
                ? new ResponseEntity<>(response, HttpStatus.NOT_FOUND)
                : new ResponseEntity<>(response, HttpStatus.OK);
    }



    @GetMapping
    public ResponseEntity<ApiResponse<List<DeliveryDto>>> getAll() {
        ApiResponse<List<DeliveryDto>> response = deliveryService.getAll();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<DeliveryDto>> findById(@PathVariable UUID id) {
        ApiResponse<DeliveryDto> response = deliveryService.findById(id);
        return response.getResult() == null
                ? new ResponseEntity<>(response, HttpStatus.NOT_FOUND)
                : new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Boolean>> delete(@PathVariable UUID id) {
        ApiResponse<Boolean> response = deliveryService.delete(id);
        return response.getResult() == false
                ? new ResponseEntity<>(response, HttpStatus.NOT_FOUND)
                : new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
    }

}
