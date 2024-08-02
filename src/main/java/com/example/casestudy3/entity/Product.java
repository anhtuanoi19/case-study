package com.example.casestudy3.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Product")
public class Product {
//    @Id
//    @GeneratedValue(strategy = GenerationType.TABLE, generator = "table_generator")
//    @TableGenerator(name = "table_generator", table = "ID_GEN", pkColumnName = "GEN_KEY", valueColumnName = "GEN_VALUE", pkColumnValue = "MY_ENTITY_ID", allocationSize = 1)
//    private Long id;

    //    Sử dụng một bảng đặc biệt trong cơ sở dữ liệu để tạo giá trị khóa chính.
//    Chiến lược này độc lập với các tính năng tự động tăng giá trị của cơ sở dữ liệu
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;

    @ManyToOne
    private Categories categories;

    @ManyToMany(mappedBy = "products")
    @JsonIgnore
    private Set<Orders> orders;

    private String status;
}
