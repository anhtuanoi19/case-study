package com.example.casestudy3.dto.request;

import lombok.Data;

import java.util.UUID;
@Data
public class DeliveryDto {
    private UUID id;
    private String type;
    private Integer status;
}
