package com.example.casestudy3.controller;

import com.example.casestudy3.dto.request.OrdersDto;
import com.example.casestudy3.dto.response.ApiResponse;
import com.example.casestudy3.entity.Orders;
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

    @PostMapping
    public ResponseEntity<ApiResponse<OrdersDto>> create(@RequestBody OrdersDto ordersDto) {
        ApiResponse<OrdersDto> response = ordersService.createOrderNew(ordersDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/create")
    public ApiResponse<OrdersDto> createOrder(@RequestBody OrdersDto orderDto, @RequestParam UUID customerId, @RequestParam UUID deliveryId) {
        return ordersService.createOrder(orderDto, customerId, deliveryId);
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

    @GetMapping("/{id}")
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

    @PutMapping("/updateNew/{id}")
    public ResponseEntity<ApiResponse<OrdersDto>> updateNew(@PathVariable UUID id, @RequestBody OrdersDto orderDto) {
        ApiResponse<OrdersDto> response = ordersService.updateOrderNew(id, orderDto);
        return ResponseEntity.status(response.getCode() == 1000 ? 200 : 400).body(response);
    }

    @PostMapping("/createOrder2")
    public ApiResponse<OrdersDto> createOrder2(@RequestBody OrdersDto ordersDto) {
        return ordersService.createOrderNew2(ordersDto);
    }

//    @PatchMapping("/{orderId}")
//    public ResponseEntity<Orders> partialUpdateOrder(@PathVariable UUID orderId, @RequestBody Date newOrderDate) {
//        try {
//            Orders updatedOrder = ordersService.partialUpdateOrder(orderId, newOrderDate);
//            return ResponseEntity.ok(updatedOrder);
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
//        }
//    }
}
