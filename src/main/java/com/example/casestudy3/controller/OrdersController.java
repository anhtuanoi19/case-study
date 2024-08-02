package com.example.casestudy3.controller;

import com.example.casestudy3.dto.request.OrdersDto;
import com.example.casestudy3.dto.response.ApiResponse;
import com.example.casestudy3.service.IOrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/order")
public class OrdersController {
    @Autowired
    private IOrdersService ordersService;

    @GetMapping("test-transaction")
    public void createOrders() throws Exception {
        ordersService.createOrders();
    }

    @PostMapping
    public ResponseEntity<ApiResponse<OrdersDto>> create(@RequestBody OrdersDto ordersDto) {
        ApiResponse<OrdersDto> response = ordersService.create(ordersDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<OrdersDto>> update(@PathVariable UUID id, @RequestBody OrdersDto ordersDto) {
        ApiResponse<OrdersDto> response = ordersService.update(ordersDto, id);
        return response.getResult() == null
                ? new ResponseEntity<>(response, HttpStatus.NOT_FOUND)
                : new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<OrdersDto>>> getAll() {
        ApiResponse<List<OrdersDto>> response = ordersService.getAll();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("get-id/{id}")
    public ResponseEntity<ApiResponse<OrdersDto>> findById(@PathVariable UUID id) {
        ApiResponse<OrdersDto> response = ordersService.findById(id);
        return response.getResult() == null
                ? new ResponseEntity<>(response, HttpStatus.NOT_FOUND)
                : new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Boolean>> delete(@PathVariable UUID id) {
        ApiResponse<Boolean> response = ordersService.delete(id);
        return response.getResult() == false
                ? new ResponseEntity<>(response, HttpStatus.NOT_FOUND)
                : new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
    }
}
