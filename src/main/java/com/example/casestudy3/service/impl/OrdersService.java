package com.example.casestudy3.service.impl;

import com.example.casestudy3.entity.Orders;
import com.example.casestudy3.service.IService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class OrdersService implements IService<Orders> {
    @Override
    public Orders create(Orders orders) {
        return null;
    }

    @Override
    public Orders update(Orders orders, UUID id) {
        return null;
    }

    @Override
    public List<Orders> getAll() {
        return null;
    }

    @Override
    public Orders findById(UUID id) {
        return null;
    }

    @Override
    public Boolean delete(UUID id) {
        return null;
    }

    @Override
    public List<Orders> findByName(String name) {
        return null;
    }
}
