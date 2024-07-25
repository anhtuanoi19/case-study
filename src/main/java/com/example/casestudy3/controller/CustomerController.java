package com.example.casestudy3.controller;

import com.example.casestudy3.dto.request.CustomerDto;
import com.example.casestudy3.dto.response.ApiResponse;
import com.example.casestudy3.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    @Autowired
    private ICustomerService customerService;

    @PostMapping
    public ResponseEntity<ApiResponse<CustomerDto>> create(@RequestBody CustomerDto customerDto) {
        ApiResponse<CustomerDto> response = customerService.create(customerDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
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
    public ResponseEntity<ApiResponse<CustomerDto>> findById(@PathVariable UUID id) {
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
