package com.example.casestudy3.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Orders_repository")
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;
    @Column(name = "order_date")
    private Date orderDate;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "customer_id")
    @JsonBackReference
    private Customer customer;

    @ManyToOne
    private Delivery delivery;

    @ManyToMany(mappedBy = "orders", cascade = CascadeType.MERGE)
    private Set<Product> products;
}
