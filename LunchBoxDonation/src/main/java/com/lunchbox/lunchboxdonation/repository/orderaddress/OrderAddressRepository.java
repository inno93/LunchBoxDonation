package com.lunchbox.lunchboxdonation.repository.orderaddress;

import com.lunchbox.lunchboxdonation.entity.Order.OrderAddress;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface OrderAddressRepository extends JpaRepository<OrderAddress, Long> {


    @Query("SELECT oa FROM OrderAddress oa " +
            "WHERE oa.modDate > :threeMonthsAgo")
    Page<OrderAddress> findByModDateAfter(LocalDateTime threeMonthsAgo, Pageable pageable);

//    List<OrderAddress> findByRegDatesAfter(LocalDateTime threeMonthsAgo);
}