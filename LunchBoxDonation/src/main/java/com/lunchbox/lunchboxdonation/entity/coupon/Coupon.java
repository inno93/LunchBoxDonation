package com.lunchbox.lunchboxdonation.entity.coupon;

import com.lunchbox.lunchboxdonation.domain.coupon.CouponrequestDto;
import com.lunchbox.lunchboxdonation.entity.Timestamp;
import groovyjarjarantlr4.v4.runtime.misc.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class Coupon extends Timestamp {
    @Id
    @GeneratedValue
    private Long id;

    @Column
    @NotNull private String coupon;

    @Column
    @NotNull private Integer price;

    @Column
    @NotNull private LocalDateTime startTime;

    @Column
    @NotNull private LocalDateTime endTime;

    @Builder
    public Coupon(CouponrequestDto couponrequestDto){
        this.coupon = couponrequestDto.getCoupon();
        this.price = couponrequestDto.getPrice();
        this.startTime = couponrequestDto.getStartTime();
        this.endTime = couponrequestDto.getEndTime();
    }

    public static Coupon of(CouponrequestDto couponrequestDto){
        return Coupon.builder()
                .couponrequestDto(couponrequestDto)
                .build();
    }
}
