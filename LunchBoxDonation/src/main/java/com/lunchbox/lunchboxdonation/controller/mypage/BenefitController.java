package com.lunchbox.lunchboxdonation.controller.mypage;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@Slf4j
public class BenefitController {




//    쿠폰
    @RequestMapping("benefit_coupon")
    public ModelAndView benefit_coupon(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("mypage/benefit_coupon");
        return mv;
    }


//    포인트
    @RequestMapping("benefit_point")
    public ModelAndView benefit_point(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("mypage/benefit_point");
        return mv;
    }


//    랭킹닭컴 상품권 관리
    @RequestMapping("benefit_voucher")
    public ModelAndView benefit_voucher(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("mypage/benefit_voucher");
        return mv;
    }
}
