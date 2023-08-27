package com.lunchbox.lunchboxdonation.repository;

import com.lunchbox.lunchboxdonation.entity.Order.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {
}
