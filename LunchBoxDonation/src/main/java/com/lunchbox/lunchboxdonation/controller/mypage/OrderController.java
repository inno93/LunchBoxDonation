package com.lunchbox.lunchboxdonation.controller.mypage;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@Slf4j
public class OrderController {
    @RequestMapping("order_before")
    public ModelAndView order_before(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("mypage/order_before");
        return mv;
    }
    @RequestMapping("order_cancel")
    public ModelAndView order_cancel(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("mypage/order_cancel");
        return mv;
    }
    @RequestMapping("order_details")
    public ModelAndView order_details(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("mypage/order_details");
        return mv;
    }
    @RequestMapping("order_regular_delivery")
    public ModelAndView order_regular_delivery(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("mypage/order_regular_delivery");
        return mv;
    }
    @RequestMapping("order_restocked")
    public ModelAndView order_restocked(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("mypage/order_restocked");
        return mv;
    }

}
