package com.lunchbox.lunchboxdonation.repository.coupon;

import com.lunchbox.lunchboxdonation.domain.coupon.CouponRequestDTO;
import com.lunchbox.lunchboxdonation.entity.coupon.Coupon;
import com.lunchbox.lunchboxdonation.entity.coupon.CouponSearch;

import com.lunchbox.lunchboxdonation.entity.coupon.QCoupon;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

import java.util.List;

import static com.lunchbox.lunchboxdonation.entity.coupon.QCoupon.coupon1;


@RequiredArgsConstructor
@Slf4j
public class CouponQueryDSLImpl implements CouponQueryDSL {
    private final JPAQueryFactory query;

    @Override
    public Page<CouponRequestDTO> couponList(Pageable pageable, CouponSearch couponSearch) {
        BooleanExpression couponContains = StringUtils.hasText(couponSearch.getCoupon()) ? coupon1.coupon.contains(couponSearch.getCoupon()) : null;

        BooleanBuilder builder = new BooleanBuilder();

        if (couponContains != null) {
            builder.or(couponContains);
        }

        final List<CouponRequestDTO> couponRequestDTOList = query.select(
                Projections.fields(
                        CouponRequestDTO.class,
                        coupon1.id,
                        coupon1.coupon,
                        coupon1.price,
                        coupon1.startTime,
                        coupon1.endTime,
                        coupon1.createdAt
                )
        ).from(coupon1)
                .where(builder)
                .orderBy(coupon1.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();


        final Long count = query.select(coupon1.count()).from(coupon1).fetchOne();


        return new PageImpl<>(couponRequestDTOList, pageable, count);
    }


}
