package com.lunchbox.lunchboxdonation.controller.admin;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@Slf4j
@RequestMapping("admin/coupon")
public class BoCouponController {

    // 쿠폰

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


    // 쿠폰 추가
    @RequestMapping("couponWrite")
    public ModelAndView couponWrite() {
        ModelAndView mv = new ModelAndView();

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
