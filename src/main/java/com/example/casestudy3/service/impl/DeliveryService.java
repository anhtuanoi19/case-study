package com.example.casestudy3.service.impl;

import com.example.casestudy3.entity.Delivery;
import com.example.casestudy3.service.IService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class DeliveryService implements IService<Delivery> {
    @Override
    public Delivery create(Delivery delivery) {
        return null;
    }

    @Override
    public Delivery update(Delivery delivery, UUID id) {
        return null;
    }

    @Override
    public List<Delivery> getAll() {
        return null;
    }

    @Override
    public Delivery findById(UUID id) {
        return null;
    }

    @Override
    public Boolean delete(UUID id) {
        return null;
    }

    @Override
    public List<Delivery> findByName(String name) {
        return null;
    }
}
