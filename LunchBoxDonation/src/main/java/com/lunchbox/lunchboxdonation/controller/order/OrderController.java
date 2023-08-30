package com.lunchbox.lunchboxdonation.controller.order;

import com.lunchbox.lunchboxdonation.entity.Order.OrderAddress;
import com.lunchbox.lunchboxdonation.service.order.OrderAddressService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("order")
@Slf4j
public class OrderController {
    @Autowired
    private OrderAddressService orderAddressService;

    @RequestMapping("kit")
    public ModelAndView kit(){
        ModelAndView mv = new ModelAndView();

        mv.setViewName("order/kit"); // html 파일 경로

        return mv;
    }
}
