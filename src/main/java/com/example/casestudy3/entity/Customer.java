package com.example.casestudy3.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Customer")
public class Customer {
    //    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private Long id;
//Đây là chiến lược mặc định. Nó cho phép JPA provider (ví dụ như Hibernate)
// tự động chọn chiến lược thích hợp dựa trên cơ sở dữ liệu bạn đang sử dụng
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;
    private String email;
    private String adress;
    private Integer status;

    @OneToMany(mappedBy = "customer", fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JsonManagedReference
    private Set<Orders> orders = new HashSet<>();
}
