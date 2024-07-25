package com.example.casestudy3.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "OrdersRepository")
public class Orders {
//    @Id
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "my_sequence")
//    @SequenceGenerator(name = "my_sequence", sequenceName = "MY_SEQUENCE", allocationSize = 1)
//    private Long id;
//    Sử dụng một sequence object trong cơ sở dữ liệu để tạo
//    giá trị cho khóa chính. Thường được sử dụng với các cơ sở dữ liệu như Oracle, PostgreSQL

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private Date orderDate;
    @ManyToOne
    private Customer customer;
    @ManyToOne
    private Delivery delivery;

}
