package com.example.casestudy3.service.impl;

import com.example.casestudy3.dto.request.OrdersDto;
import com.example.casestudy3.dto.response.ApiResponse;
import com.example.casestudy3.entity.Log;
import com.example.casestudy3.entity.Orders;
import com.example.casestudy3.exception.ErrorCode;
import com.example.casestudy3.repository.LogRepository;
import com.example.casestudy3.repository.OrdersRepository;
import com.example.casestudy3.service.IOrdersService;
import com.example.casestudy3.tranferDatas.OrdersMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class OrdersService implements IOrdersService {
    @Autowired
    private OrdersRepository ordersRepository;
    private final OrdersMapper ordersMapper = OrdersMapper.INSTANCE;

    @Autowired
    private LogRepository logRepository;

    public void saveLog(String message) {
        Log log = new Log();
        log.setMessage(message);
        logRepository.save(log);
    }


    // VD1
//    @Transactional(propagation = Propagation.REQUIRES_NEW) // VD4
    @Transactional // VD3
    public void createOrder() {

        System.out.println("------ createOrder ------");
        Orders order = new Orders();
        order.setOrderDate(new Date());
        saveLog("This is createOrder method with runtime exception");
        ordersRepository.save(order);

// VD5 //
//        throw new RuntimeException("Create Order RuntimeException");
    }

    // VD2
    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW,
            rollbackFor = SQLException.class)
    public void createOrders() throws Exception {
        try { // VD3
            System.out.println("------ createOrder ------");
            Orders order = new Orders();
            order.setOrderDate(new Date());
            saveLog("2 This is createOrder method with runtime exception");
            ordersRepository.save(order);

            throw new SQLException();
        } catch (Exception e) {
            System.out.println("----------Here error---------------");
            saveLog(ErrorCode.SQL_ERROR.getMessage());
        }
    }

    @Override
    public ApiResponse<OrdersDto> create(OrdersDto ordersDto) {
        Orders orders = ordersMapper.toEntity(ordersDto);
        orders = ordersRepository.save(orders);
        OrdersDto result = ordersMapper.toDto(orders);
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
        Orders orders = ordersMapper.toEntity(ordersDto);
        orders.setId(id);
        orders = ordersRepository.save(orders);
        OrdersDto result = ordersMapper.toDto(orders);
        ApiResponse<OrdersDto> apiResponse = new ApiResponse<>();
        apiResponse.setResult(result);
        return apiResponse;
    }

    @Override
    public ApiResponse<List<OrdersDto>> getAll() {
        List<Orders> ordersList = ordersRepository.findAll();
        List<OrdersDto> ordersDtoList = ordersMapper.toDtoList(ordersList);
        ApiResponse<List<OrdersDto>> apiResponse = new ApiResponse<>();
        apiResponse.setResult(ordersDtoList);
        apiResponse.setMessage(ordersDtoList != null ? "Thanh cong order" : "That bai order");
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
        OrdersDto result = ordersMapper.toDto(orders);
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
}
