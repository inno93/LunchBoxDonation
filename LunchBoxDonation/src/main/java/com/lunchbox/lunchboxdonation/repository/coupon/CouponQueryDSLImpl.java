package com.lunchbox.lunchboxdonation.repository.coupon;

import com.lunchbox.lunchboxdonation.domain.coupon.CouponRequestDTO;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
public class CouponQueryDSLImpl implements CouponQueryDSL {
    private final JPAQueryFactory query;


}
