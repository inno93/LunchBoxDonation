package com.lunchbox.lunchboxdonation.controller.admin;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequestMapping("admin/order")
public class BoOrderController {

    // 주문 목록
    @GetMapping("orderList")
    public void orderList(){

    }
//    주문 상세보기
    @GetMapping("orderDetail")
    public void orderDetail(){

    }

    // 기부
    //

}
