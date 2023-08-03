package com.lunchbox.lunchboxdonation.entity.coupon;

import com.lunchbox.lunchboxdonation.entity.member.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "TBL_MYCOUPON")
public class MyCoupon{
    @Id @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID", nullable = false)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "COUPON_ID", nullable = false)
    private Coupon coupon;

    @Builder
    public MyCoupon(Member member, Coupon coupon) {
        this.member = member;
        this.coupon = coupon;
    }

    public static MyCoupon of(Member member, Coupon coupon){
        return MyCoupon.builder()
                .member(member)
                .coupon(coupon).build();
    }
}
