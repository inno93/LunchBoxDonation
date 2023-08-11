package com.lunchbox.lunchboxdonation.service.coupon;

import com.lunchbox.lunchboxdonation.entity.coupon.Coupon;
import com.lunchbox.lunchboxdonation.entity.coupon.MyCoupon;
import com.lunchbox.lunchboxdonation.entity.member.Member;
import com.lunchbox.lunchboxdonation.exception.CustomException;
import com.lunchbox.lunchboxdonation.exception.Error;
import com.lunchbox.lunchboxdonation.repository.coupon.CouponRepository;
import com.lunchbox.lunchboxdonation.repository.member.MemberRepository;
import com.lunchbox.lunchboxdonation.repository.coupon.MyCouPonRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class MyCouponService {

    private final MyCouPonRepository myCouPonRepository;
    private final MemberRepository memberRepository;
    private final CouponRepository couponRepository;


    public MyCoupon assignCouponToMember(Long memberId) {
        Member member = memberRepository.findById(memberId).orElse(null);
        if (member == null) {
            throw new CustomException(Error.NO_COUPON_AVAILABLE);
        }
        Coupon coupon = couponRepository.findFirstAvailableCoupon();
        if (coupon == null) {
            throw new CustomException(Error.NO_COUPON_AVAILABLE);
        }
        MyCoupon myCoupon = MyCoupon.of(member, coupon);
        return myCouPonRepository.save(myCoupon);
    }
}
