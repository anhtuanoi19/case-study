package com.example.casestudy3.repository;

import com.example.casestudy3.dto.request.NameDto;
import com.example.casestudy3.entity.Categories;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

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

    @Query(nativeQuery = true, value = "SELECT id, code from Categories")
    Page<Categories> findAll(Pageable pageable);

    @Query(nativeQuery = true, value = "select p.name, c.name as categoryName from categories c " +
            " join product p on c.id= p.categories_id ")
    List<NameDto> joinCategoryAndProduct();


}
