package com.example.casestudy3.dto.request;

import com.example.casestudy3.entity.Customer;
import com.example.casestudy3.entity.Delivery;
import lombok.Data;

import java.util.Date;
import java.util.UUID;
@Data
public class OrdersDto {
    private UUID id;
    private Date orderDate;
    private Customer customer;
    private Delivery delivery;
}
