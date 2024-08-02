package com.example.casestudy3.dto.request;

import com.example.casestudy3.entity.Orders;
import com.example.casestudy3.entity.Product;

import java.util.Set;

public class CustomerWithOrderDto {
    private Product product;
    private Set<Orders> orders;
}
