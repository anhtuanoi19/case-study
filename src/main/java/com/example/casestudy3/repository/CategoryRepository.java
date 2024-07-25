package com.example.casestudy3.repository;

import com.example.casestudy3.entity.Categories;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
@Repository
public interface CategoryRepository extends JpaRepository<Categories, UUID> {

    List<Categories> findAllByName(String name);

    boolean existsByCode(String code);

    @Query("SELECT c FROM Categories c WHERE c.status = :status")
    List<Categories> findByStatus(@Param("status") Integer status);

    @Query("SELECT c FROM Categories c WHERE c.status = :status")
    Page<Categories> findByStatus(@Param("status") Integer status, Pageable pageable);
}
