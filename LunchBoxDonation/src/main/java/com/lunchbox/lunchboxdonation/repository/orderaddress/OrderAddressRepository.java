package com.lunchbox.lunchboxdonation.repository.orderaddress;

import com.lunchbox.lunchboxdonation.entity.Order.OrderAddress;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface OrderAddressRepository extends JpaRepository<OrderAddress, Long> {


    @Query("SELECT oa FROM OrderAddress oa " +
            "WHERE oa.modDate > :threeMonthsAgo")
    Page<OrderAddress> findByModDateAfter(LocalDateTime threeMonthsAgo, Pageable pageable);

    @Query("SELECT oa FROM OrderAddress oa")
    List<OrderAddress> findAll();

    @Query("select oa from OrderAddress oa where oa.order.member.id = :memberId")
    Optional<OrderAddress> findByMemberId(Long memberId);
}