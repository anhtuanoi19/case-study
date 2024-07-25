package com.example.casestudy3.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Delivery")
@Entity
public class Delivery {

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//    Với chiến lược này, giá trị khóa chính sẽ được tạo bởi cột auto-increment
//    trong cơ sở dữ liệu. Thường được sử dụng với các cơ sở dữ liệu như MySQL, SQL Server

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String type;

    private Integer status;
}
