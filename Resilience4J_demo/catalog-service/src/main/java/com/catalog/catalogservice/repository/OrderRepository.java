package com.catalog.catalogservice.repository;

import com.catalog.catalogservice.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Orders,Integer> {

    List<Orders> findByCategory(String category);
    List<Orders> findByName(String name);
}


