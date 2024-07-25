package com.example.casestudy3.dto.request;

import lombok.Data;

import java.util.UUID;
@Data
public class CustomerDto {


    private UUID id;
    private String name;
    private String email;
    private String adress;
    private Integer status;
}
