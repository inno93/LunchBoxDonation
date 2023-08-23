package com.lunchbox.lunchboxdonation.domain.coupon;

import lombok.Data;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
public class CouponRequestDTO {
    private String coupon;
    private Integer price;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

}
