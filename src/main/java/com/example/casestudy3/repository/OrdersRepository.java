package com.example.casestudy3.repository;

import com.example.casestudy3.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface OrdersRepository extends JpaRepository<Orders, UUID> {
}
