package com.lunchbox.lunchboxdonation.repository.coupon;

import com.lunchbox.lunchboxdonation.entity.coupon.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CouponRepository extends JpaRepository<Coupon,Long> {

    @Query("SELECT c FROM Coupon c WHERE c.endTime > CURRENT_TIMESTAMP AND c.price IS NOT NULL ORDER BY c.startTime ASC")
    Coupon findFirstAvailableCoupon();
}
