package com.example.casestudy3.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.UUID;

@Data
public class CategoryDto {
    private UUID id;
    private String name;
    private String code;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Integer status;
}
