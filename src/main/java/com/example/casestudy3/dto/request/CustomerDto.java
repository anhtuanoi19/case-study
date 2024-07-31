package com.example.casestudy3.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.UUID;

@Data
public class CustomerDto {

    private UUID id;
    @NotNull(message = "{ST005}")
    @Size(min = 3, max = 50, message = "{ST006}")
    private String name;
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "{ST011}")
    private String email;
    @NotNull(message = "{ST010}")
    private String adress;
    private Integer status;
}
