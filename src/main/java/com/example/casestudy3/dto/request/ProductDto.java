package com.example.casestudy3.dto.request;

import com.example.casestudy3.entity.Categories;
import com.example.casestudy3.entity.Orders;
import lombok.Data;

import java.util.Set;
import java.util.UUID;
@Data
public class ProductDto {
    private UUID id;
    private String name;
    private Categories categories;
    private String status;
}
