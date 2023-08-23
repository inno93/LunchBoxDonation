package com.lunchbox.lunchboxdonation.controller.admin;

import com.lunchbox.lunchboxdonation.config.FileUtils;
import com.lunchbox.lunchboxdonation.domain.coupon.CouponRequestDTO;
import com.lunchbox.lunchboxdonation.service.coupon.CouponService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("admin/coupon")
public class BoCouponController {
    private final CouponService couponService;


    // 쿠폰 리스트
    @RequestMapping("couponList")
    public ModelAndView couponList() {
        ModelAndView mv = new ModelAndView();

        return mv;
    }
    // 쿠폰 상세보기
    @RequestMapping("couponDetail")
    public ModelAndView couponDetail() {
        ModelAndView mv = new ModelAndView();

        return mv;
    }


    // 쿠폰 등록 페이지 이동
    @GetMapping("couponWrite")
    public ModelAndView couponWrite() {
        ModelAndView mv = new ModelAndView();

        mv.setViewName("/admin/coupon/couponWrite");
        return mv;
    }

    //쿠폰 등록
    @PostMapping("save")
    public ModelAndView save(@ModelAttribute CouponRequestDTO couponRequestDTO){
        ModelAndView mv = new ModelAndView();
        log.info("{}",couponRequestDTO.toString());
        Long id = couponService.insert(couponRequestDTO);

        mv.setViewName("/admin/coupon/couponList");



        return mv;
    }



    // 쿠폰 삭제
    @RequestMapping("remove")
    public ModelAndView removeCoupon() {
        ModelAndView mv = new ModelAndView();

        return mv;
    }
//    쿠폰 수정
    @RequestMapping("couponModify")
    public ModelAndView couponModify() {
        ModelAndView mv = new ModelAndView();

        return mv;
    }

}
