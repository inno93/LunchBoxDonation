package com.lunchbox.lunchboxdonation.entity.coupon;

import com.lunchbox.lunchboxdonation.entity.Member;
import com.lunchbox.lunchboxdonation.entity.Timestamp;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class MyCoupon extends Timestamp {
    @Id @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID", nullable = false)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "COUPON_ID", nullable = false)
    private Coupon coupon;
}
