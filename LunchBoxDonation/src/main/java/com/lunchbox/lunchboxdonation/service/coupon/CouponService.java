package com.lunchbox.lunchboxdonation.service.coupon;

import com.lunchbox.lunchboxdonation.domain.coupon.CouponRequestDTO;
import com.lunchbox.lunchboxdonation.entity.coupon.Coupon;
import com.lunchbox.lunchboxdonation.repository.coupon.CouponRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class CouponService {

    private final CouponRepository couponRepository;


    public Coupon createCoupon(CouponRequestDTO couponRequestDTO) {
        Coupon coupon = Coupon.of(couponRequestDTO);
        return couponRepository.save(coupon);
    }
}
