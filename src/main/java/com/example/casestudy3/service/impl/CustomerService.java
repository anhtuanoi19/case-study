package com.example.casestudy3.service.impl;

import com.example.casestudy3.dto.request.CustomerDto;
import com.example.casestudy3.dto.response.ApiResponse;
import com.example.casestudy3.entity.Customer;
import com.example.casestudy3.entity.Orders;
import com.example.casestudy3.repository.CustomerRepository;
import com.example.casestudy3.service.ICustomerService;
import com.example.casestudy3.tranferDatas.CustomerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
public class CustomerService implements ICustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    private final CustomerMapper customerMapper = CustomerMapper.INSTANCE;

    public void createOrderByCustomer(UUID customer_id, Orders newOrders) {
        Customer customer = customerRepository.findById(customer_id)
                .orElseThrow(() -> new RuntimeException("Customer not found"));


        newOrders.setCustomer(customer);
        customer.getOrders().add(newOrders);

        customerRepository.save(customer);

//        Customer customer = customerRepository.findById(customerId)
//                .orElseThrow(() -> new RuntimeException("Customer not found"));
//
//        newOrder.setCustomer(customer);
//        customer.getOrders().add(newOrder);
//
//        orderRepository.save(newOrder); // Save the new order
//        customerRepository.save(customer); // Save the updated customer
//
//        return customer;
    }

    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }

    public Customer createCustomerWithOrder(Customer newCustomer) {

        return customerRepository.save(newCustomer);
    }

    public void testLazyLoading(UUID customerId) {
        Customer customer = customerRepository.findById(customerId).orElse(null);

        Set<Orders> orders = new HashSet<>();

        if (customer != null) {
            System.out.println("Customer Name: " + customer.getName());

            // Đến đây, chưa có truy vấn nào để tải orders
            System.out.println("Orders: " + customer.getOrders().size());

//          Trong console log, bạn sẽ thấy truy vấn cho bảng order chỉ được thực thi khi bạn gọi
//          customer.getOrders().size().Trong console log, bạn sẽ thấy truy vấn cho bảng order chỉ được thực thi khi bạn gọi customer.getOrders().size().
//          Truy vấn để tải orders sẽ được thực thi khi truy cập thuộc tính orders
            for (Orders order : customer.getOrders()) {
                System.out.println("Order Date is: " + order.getOrderDate());
            }
        }
    }

    @Override
    public ApiResponse<CustomerDto> create(CustomerDto customerDto) {
        Customer customer = customerMapper.toEntity(customerDto);
        customer = customerRepository.save(customer);
        CustomerDto result = customerMapper.toDto(customer);
        ApiResponse<CustomerDto> apiResponse = new ApiResponse<CustomerDto>();
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
        Customer customer = customerMapper.toEntity(customerDto);
        customer.setId(id);
        customer = customerRepository.save(customer);
        CustomerDto result = customerMapper.toDto(customer);
        ApiResponse<CustomerDto> apiResponse = new ApiResponse<>();
        apiResponse.setResult(result);
        return apiResponse;
    }

    @Override
    public ApiResponse<List<CustomerDto>> getAll() {
        List<Customer> customerList = customerRepository.findAll();
        List<CustomerDto> customerDtoList = customerMapper.toDtoList(customerList);
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
        CustomerDto result = customerMapper.toDto(customer);
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
