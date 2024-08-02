package com.example.casestudy3.controller;

import com.example.casestudy3.dto.request.CustomerDto;
import com.example.casestudy3.dto.response.ApiResponse;
import com.example.casestudy3.entity.Customer;
import com.example.casestudy3.entity.Orders;
import com.example.casestudy3.service.ICustomerService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/customers")
@Validated
public class CustomerController {

    @Autowired
    private ICustomerService customerService;

    @GetMapping("test-lazy/{id}")
    public void testLazyLoading(@PathVariable("id") UUID customerId) {
        customerService.testLazyLoading(customerId);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<CustomerDto>> create(@RequestBody @Valid CustomerDto customerDto) {
        ApiResponse<CustomerDto> response = customerService.create(customerDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("test-persits")
    public Customer createCustomerWithOrder(@RequestBody Customer newCustomer) {
        return customerService.createCustomerWithOrder(newCustomer);
    }

    @PutMapping("customer-update-order/{id}")
    public void createOrderByCustomer(@PathVariable("id") UUID customer_id, @RequestBody Orders newOrders) {
        customerService.createOrderByCustomer(customer_id, newOrders);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<CustomerDto>> update(@PathVariable UUID id, @RequestBody CustomerDto customerDto) {
        ApiResponse<CustomerDto> response = customerService.update(customerDto, id);
        return response.getResult() == null
                ? new ResponseEntity<>(response, HttpStatus.NOT_FOUND)
                : new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<CustomerDto>>> getAll() {
        ApiResponse<List<CustomerDto>> response = customerService.getAll();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<CustomerDto>> findById(@PathVariable @NotNull(message = "{ST003}") UUID id) {
        ApiResponse<CustomerDto> response = customerService.findById(id);
        return response.getResult() == null
                ? new ResponseEntity<>(response, HttpStatus.NOT_FOUND)
                : new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Boolean>> delete(@PathVariable UUID id) {
        ApiResponse<Boolean> response = customerService.delete(id);
        return response.getResult() == false
                ? new ResponseEntity<>(response, HttpStatus.NOT_FOUND)
                : new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
    }
}
