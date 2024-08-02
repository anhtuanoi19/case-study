package com.example.casestudy3.service.impl;

import com.example.casestudy3.dto.request.OrdersDto;
import com.example.casestudy3.dto.response.ApiResponse;
import com.example.casestudy3.entity.Customer;
import com.example.casestudy3.entity.Delivery;
import com.example.casestudy3.entity.Orders;
import com.example.casestudy3.entity.Product;
import com.example.casestudy3.exception.AppException;
import com.example.casestudy3.exception.ErrorCode;
import com.example.casestudy3.repository.CustomerRepository;
import com.example.casestudy3.repository.DeliveryRepository;
import com.example.casestudy3.repository.OrdersRepository;
import com.example.casestudy3.repository.ProductRepository;
import com.example.casestudy3.service.IOrdersService;
import com.example.casestudy3.tranferDatas.OrdersMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class OrdersService implements IOrdersService {
    @Autowired
    private OrdersRepository ordersRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private DeliveryRepository deliveryRepository;

    @Autowired
    private ProductRepository productRepository;


    //Nếu có một transaction đang tồn tại, phương thức hiện tại sẽ chạy trong transaction đó.
    //Nếu không có transaction nào đang tồn tại, một transaction mới sẽ được tạo ra.
    //sử dụng default
    @Transactional(propagation = Propagation.REQUIRED)
    public ApiResponse<OrdersDto> createOrder(OrdersDto orderDto, UUID customerId, UUID deliveryId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new AppException(ErrorCode.CUSTOMER_NOT_EXISTED));
        Delivery delivery = deliveryRepository.findById(deliveryId)
                .orElseThrow(() -> new AppException(ErrorCode.DELIVERY_NOT_EXISTED));

        Orders order = OrdersMapper.INSTANCE.toEntity(orderDto);
        order.setCustomer(customer);
        order.setDelivery(delivery);
        ordersRepository.save(order);

        ApiResponse<OrdersDto> apiResponse = new ApiResponse<>();
        apiResponse.setResult(OrdersMapper.INSTANCE.toDto(order));
        apiResponse.setMessage("Order created successfully");

        return apiResponse;
    }

    //ví dụ thêm 3 bảng cùng 1 lúc
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public ApiResponse<OrdersDto> createOrderNew(OrdersDto orderDto) {
        Customer customer = OrdersMapper.INSTANCE.toCustomerEntity(orderDto.getCustomer());
        customerRepository.save(customer);

        Delivery delivery = OrdersMapper.INSTANCE.toDeliveryEntity(orderDto.getDelivery());
        deliveryRepository.save(delivery);

        Orders order = OrdersMapper.INSTANCE.toEntity(orderDto);
        order.setCustomer(customer);
        order.setDelivery(delivery);
        ordersRepository.save(order);

        ApiResponse<OrdersDto> apiResponse = new ApiResponse<>();
        apiResponse.setResult(OrdersMapper.INSTANCE.toDto(order));
        apiResponse.setMessage("Order created successfully");

        return apiResponse;
    }

    @Override
    public ApiResponse<OrdersDto> create(OrdersDto ordersDto) {
        Orders orders = OrdersMapper.INSTANCE.toEntity(ordersDto);
        orders = ordersRepository.save(orders);
        OrdersDto result = OrdersMapper.INSTANCE.toDto(orders);
        ApiResponse<OrdersDto> apiResponse = new ApiResponse<>();
        apiResponse.setResult(result);
        return apiResponse;
    }

    @Override
    public ApiResponse<OrdersDto> update(OrdersDto ordersDto, UUID id) {
        Orders existingOrders = ordersRepository.findById(id).orElse(null);
        if (existingOrders == null) {
            ApiResponse<OrdersDto> apiResponse = new ApiResponse<>();
            apiResponse.setMessage("Order not found");
            return apiResponse;
        }
        Orders orders = OrdersMapper.INSTANCE.toEntity(ordersDto);
        orders.setId(id);
        orders = ordersRepository.save(orders);
        OrdersDto result = OrdersMapper.INSTANCE.toDto(orders);
        ApiResponse<OrdersDto> apiResponse = new ApiResponse<>();
        apiResponse.setResult(result);
        return apiResponse;
    }

    @Override
    public ApiResponse<List<OrdersDto>> getAll() {
        List<Orders> ordersList = ordersRepository.findAll();
        List<OrdersDto> ordersDtoList = OrdersMapper.INSTANCE.toListDto(ordersList);
        ApiResponse<List<OrdersDto>> apiResponse = new ApiResponse<>();
        apiResponse.setResult(ordersDtoList);
        return apiResponse;
    }

    @Override
    public ApiResponse<OrdersDto> findById(UUID id) {
        Orders orders = ordersRepository.findById(id).orElse(null);
        if (orders == null) {
            ApiResponse<OrdersDto> apiResponse = new ApiResponse<>();
            apiResponse.setMessage("Order not found");
            return apiResponse;
        }
        OrdersDto result = OrdersMapper.INSTANCE.toDto(orders);
        ApiResponse<OrdersDto> apiResponse = new ApiResponse<>();
        apiResponse.setResult(result);
        return apiResponse;
    }

    @Override
    public ApiResponse<Boolean> delete(UUID id) {
        if (!ordersRepository.existsById(id)) {
            ApiResponse<Boolean> apiResponse = new ApiResponse<>();
            apiResponse.setMessage("Order not found");
            apiResponse.setResult(false);
            return apiResponse;
        }
        ordersRepository.deleteById(id);
        ApiResponse<Boolean> apiResponse = new ApiResponse<>();
        apiResponse.setResult(true);
        return apiResponse;
    }

    @Transactional
    public ApiResponse<OrdersDto> createOrderNew2(OrdersDto ordersDto) {
        Orders order = OrdersMapper.INSTANCE.toEntity(ordersDto);

        // Cập nhật danh sách sản phẩm
        Set<Product> products = ordersDto.getProductIds().stream()
                .map(productDto -> {
                    Optional<Product> optionalProduct = productRepository.findById(productDto.getId());
                    return optionalProduct.orElse(null);
                })
                .collect(Collectors.toSet());

        order.setProducts(products);

        // Lưu đơn hàng mới
        Orders savedOrder = ordersRepository.save(order);

        // Tạo phản hồi API
        ApiResponse<OrdersDto> apiResponse = new ApiResponse<>();
        apiResponse.setCode(1000);
        apiResponse.setResult(OrdersMapper.INSTANCE.toDto(savedOrder));
        return apiResponse;
    }
    //
    public ApiResponse<OrdersDto> updateOrderNew(UUID id, OrdersDto ordersDto) {
        Orders optionalOrder = ordersRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.UNCATEGORIZED_EXCEPTION));
        ApiResponse<OrdersDto> apiResponse = new ApiResponse<>();
        optionalOrder.setOrderDate(ordersDto.getOrderDate());

        // Cập nhật danh sách sản phẩm
        Set<Product> products = ordersDto.getProductIds().stream()
                .map(productDto -> {
                    Optional<Product> optionalProduct = productRepository.findById(productDto.getId());
                    return optionalProduct.orElse(null);
                })
                .collect(Collectors.toSet());

        optionalOrder.setProducts(products);

        // Lưu đơn hàng đã cập nhật
        Orders updatedOrder = ordersRepository.save(optionalOrder);
        apiResponse.setResult(OrdersMapper.INSTANCE.toDto(updatedOrder));
        return apiResponse;
    }


}
