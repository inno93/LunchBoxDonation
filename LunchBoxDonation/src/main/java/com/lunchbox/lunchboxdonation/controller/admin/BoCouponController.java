package com.lunchbox.lunchboxdonation.controller.admin;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@Slf4j
@RequestMapping("coupon")
public class BoCouponController {

    // 쿠폰

    // 쿠폰 리스트
    @RequestMapping("list")
    public ModelAndView list() {
        ModelAndView mv = new ModelAndView();

        return mv;
    }

    // 쿠폰 추가
    @RequestMapping("addCoupon")
    public ModelAndView addCoupon() {
        ModelAndView mv = new ModelAndView();

        return mv;
    }

    // 쿠폰 삭제
    @RequestMapping("remove")
    public ModelAndView removeCoupon() {
        ModelAndView mv = new ModelAndView();

        return mv;
    }

    @RequestMapping("modify")
    public ModelAndView modifyCoupon() {
        ModelAndView mv = new ModelAndView();

        return mv;
    }

}
