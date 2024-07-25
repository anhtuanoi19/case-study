package com.example.casestudy3.service.impl;

import com.example.casestudy3.dto.request.CustomerDto;
import com.example.casestudy3.dto.response.ApiResponse;
import com.example.casestudy3.entity.Customer;
import com.example.casestudy3.repository.CustomerRepository;
import com.example.casestudy3.service.ICustomerService;
import com.example.casestudy3.tranferDatas.CustomerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CustomerService implements ICustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public ApiResponse<CustomerDto> create(CustomerDto customerDto) {
        Customer customer = CustomerMapper.INSTANCE.toEntity(customerDto);
        customer = customerRepository.save(customer);
        CustomerDto result = CustomerMapper.INSTANCE.toDto(customer);
        ApiResponse<CustomerDto> apiResponse = new ApiResponse<>();
        apiResponse.setResult(result);
        return apiResponse;
    }

    @Override
    public ApiResponse<CustomerDto> update(CustomerDto customerDto, UUID id) {
        Customer existingCustomer = customerRepository.findById(id).orElse(null);
        if (existingCustomer == null) {
            ApiResponse<CustomerDto> apiResponse = new ApiResponse<>();
            apiResponse.setMessage("Customer not found");
            return apiResponse;
        }
        Customer customer = CustomerMapper.INSTANCE.toEntity(customerDto);
        customer.setId(id);
        customer = customerRepository.save(customer);
        CustomerDto result = CustomerMapper.INSTANCE.toDto(customer);
        ApiResponse<CustomerDto> apiResponse = new ApiResponse<>();
        apiResponse.setResult(result);
        return apiResponse;
    }

    @Override
    public ApiResponse<List<CustomerDto>> getAll() {
        List<Customer> customerList = customerRepository.findAll();
        List<CustomerDto> customerDtoList = CustomerMapper.INSTANCE.toDtoList(customerList);
        ApiResponse<List<CustomerDto>> apiResponse = new ApiResponse<>();
        apiResponse.setResult(customerDtoList);
        return apiResponse;
    }

    @Override
    public ApiResponse<CustomerDto> findById(UUID id) {
        Customer customer = customerRepository.findById(id).orElse(null);
        if (customer == null) {
            ApiResponse<CustomerDto> apiResponse = new ApiResponse<>();
            apiResponse.setMessage("Customer not found");
            return apiResponse;
        }
        CustomerDto result = CustomerMapper.INSTANCE.toDto(customer);
        ApiResponse<CustomerDto> apiResponse = new ApiResponse<>();
        apiResponse.setResult(result);
        return apiResponse;
    }

    @Override
    public ApiResponse<Boolean> delete(UUID id) {
        if (!customerRepository.existsById(id)) {
            ApiResponse<Boolean> apiResponse = new ApiResponse<>();
            apiResponse.setMessage("Customer not found");
            apiResponse.setResult(false);
            return apiResponse;
        }
        customerRepository.deleteById(id);
        ApiResponse<Boolean> apiResponse = new ApiResponse<>();
        apiResponse.setResult(true);
        return apiResponse;
    }
}
