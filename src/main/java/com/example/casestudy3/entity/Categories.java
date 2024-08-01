package com.example.casestudy3.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
@Table( name = "Categories")
@JsonIgnoreProperties("{name}")
public class Categories {
    //Dùng UUID (Universally Unique Identifier) để tạo giá trị khóa chính.
//UUID là một giá trị 128-bit duy nhất.
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;
    private String code;
    private Integer status;

}
