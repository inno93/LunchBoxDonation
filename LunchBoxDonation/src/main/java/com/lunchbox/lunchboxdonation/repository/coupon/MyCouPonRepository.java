package com.lunchbox.lunchboxdonation.repository.coupon;

import com.lunchbox.lunchboxdonation.entity.coupon.MyCoupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MyCouPonRepository extends JpaRepository<MyCoupon,Long> {
}
