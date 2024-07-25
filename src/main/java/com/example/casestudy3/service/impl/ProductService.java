package com.example.casestudy3.service.impl;

import com.example.casestudy3.entity.Product;
import com.example.casestudy3.service.IService;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;
import java.util.UUID;

@Service
public class ProductService implements IService<Product> {

    @Override
    public Product create(Product product) {
        return null;
    }

    @Override
    public Product update(Product product, UUID id) {
        return null;
    }

    @Override
    public List<Product> getAll() {
        return null;
    }

    @Override
    public Product findById(UUID id) {
        return null;
    }

    @Override
    public Boolean delete(UUID id) {
        return null;
    }

    @Override
    public List<Product> findByName(String name) {
        return null;
    }
}
