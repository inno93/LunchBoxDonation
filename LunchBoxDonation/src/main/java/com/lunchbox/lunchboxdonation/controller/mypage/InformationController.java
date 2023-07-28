package com.lunchbox.lunchboxdonation.controller.mypage;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@Slf4j
public class InformationController {

    @RequestMapping("information_crystal")
    public ModelAndView information_crystal(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("mypage/information_crystal");
        return mv;
    }
    @RequestMapping("information_shipping_address")
    public ModelAndView information_shipping_address(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("mypage/information_shipping_address");
        return mv;
    }
}
