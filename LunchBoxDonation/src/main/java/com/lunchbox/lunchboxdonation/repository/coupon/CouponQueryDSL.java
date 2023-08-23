package com.lunchbox.lunchboxdonation.repository.coupon;

import com.lunchbox.lunchboxdonation.domain.coupon.CouponRequestDTO;
import com.lunchbox.lunchboxdonation.entity.coupon.Coupon;
import com.lunchbox.lunchboxdonation.entity.coupon.CouponSearch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CouponQueryDSL {

    public Page<CouponRequestDTO> couponList(Pageable pageable, CouponSearch couponSearch);

}
