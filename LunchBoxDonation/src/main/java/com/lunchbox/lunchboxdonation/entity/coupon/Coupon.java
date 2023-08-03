package com.lunchbox.lunchboxdonation.entity.coupon;

import com.lunchbox.lunchboxdonation.domain.coupon.CouponRequestDTO;
import com.lunchbox.lunchboxdonation.entity.Timestamp;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "TBL_COUPON")
public class Coupon extends Timestamp {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String coupon;
    private Integer price;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    @Builder
    public Coupon(CouponRequestDTO couponrequestDTO){
        this.coupon = couponrequestDTO.getCoupon();
        this.price = couponrequestDTO.getPrice();
        this.startTime = couponrequestDTO.getStartTime();
        this.endTime = couponrequestDTO.getEndTime();
    }

    public static Coupon of(CouponRequestDTO couponrequestDTO){
        return Coupon.builder()
                .couponrequestDTO(couponrequestDTO)
                .build();
    }
}
