package com.lunchbox.lunchboxdonation.service.coupon;

import com.lunchbox.lunchboxdonation.domain.coupon.CouponRequestDTO;
import com.lunchbox.lunchboxdonation.entity.coupon.Coupon;
import com.lunchbox.lunchboxdonation.entity.coupon.CouponSearch;
import com.lunchbox.lunchboxdonation.repository.coupon.CouponRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class CouponService {

    private final CouponRepository couponRepository;


    public Coupon createCoupon(CouponRequestDTO couponRequestDTO) {
        Coupon coupon = Coupon.of(couponRequestDTO);
        return couponRepository.save(coupon);
    }

    public Long insert(CouponRequestDTO couponRequestDTO){
        Coupon coupon = Coupon.of(couponRequestDTO);
        return couponRepository.save(coupon).getId();
    }

    public Page<CouponRequestDTO>  couponList(Pageable pageable, CouponSearch couponSearch){
        return couponRepository.couponList(pageable, couponSearch);
    }

    public void deleteCouponById(Long id){
        couponRepository.deleteById(id);
    }

    public Coupon getCouponById(Long id){
        return couponRepository.findById(id).get();
    }

    public Long updateCoupon(CouponRequestDTO couponRequestDTO){
        Optional<Coupon> coupon = couponRepository.findById(couponRequestDTO.getId());
        if(coupon.isPresent()){
           Coupon data = coupon.get();
           data.setCoupon(couponRequestDTO.getCoupon());
           data.setPrice(couponRequestDTO.getPrice());
           data.setStartTime(couponRequestDTO.getStartTime());
           data.setEndTime(couponRequestDTO.getEndTime());
           couponRepository.save(data);
        }
        return  couponRequestDTO.getId();

    }
}
