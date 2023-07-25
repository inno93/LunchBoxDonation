package com.lunchbox.lunchboxdonation.controller.mypage;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@Slf4j
public class BenefitController {
    @RequestMapping("mypage")
    public ModelAndView benefit_coupon(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("mypage/benefit_coupon");
        return mv;
    }
    @RequestMapping("mypage")
    public ModelAndView benefit_point(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("mypage/benefit_point");
        return mv;
    }
    @RequestMapping("mypage")
    public ModelAndView benefit_voucher(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("mypage/benefit_voucher");
        return mv;
    }
}
