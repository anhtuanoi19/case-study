package com.example.casestudy3.service.impl;

import com.example.casestudy3.entity.Customer;
import com.example.casestudy3.service.IService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CustomerService implements IService<Customer> {

    @Override
    public Customer create(Customer customer) {
        return null;
    }

    @Override
    public Customer update(Customer customer, UUID id) {
        return null;
    }

    @Override
    public List<Customer> getAll() {
        return null;
    }

    @Override
    public Customer findById(UUID id) {
        return null;
    }

    @Override
    public Boolean delete(UUID id) {
        return null;
    }

    @Override
    public List<Customer> findByName(String name) {
        return null;
    }
}
