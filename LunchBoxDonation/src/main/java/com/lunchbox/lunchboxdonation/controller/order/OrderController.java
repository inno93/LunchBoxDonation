package com.lunchbox.lunchboxdonation.controller.order;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("order")
@Slf4j
public class OrderController {

    @RequestMapping("kit")
    public ModelAndView kit(){
        ModelAndView mv = new ModelAndView();

        mv.setViewName("order/kit"); // html 파일 경로

        return mv;
    }

    @RequestMapping("order")
    public ModelAndView order(){
        ModelAndView mv = new ModelAndView();

        mv.setViewName("order/order"); // html 파일 경로

        return mv;
    }

}
