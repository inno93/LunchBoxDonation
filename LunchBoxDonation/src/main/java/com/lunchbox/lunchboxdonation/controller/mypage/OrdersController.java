package com.lunchbox.lunchboxdonation.controller.mypage;

import com.lunchbox.lunchboxdonation.entity.Order.OrderAddress;
import com.lunchbox.lunchboxdonation.service.mypage.MypageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Controller
@Slf4j
@RequiredArgsConstructor
public class OrdersController {


    private final MypageService mypageService;


//    이전 주문 내역
    @RequestMapping("order_before")
    public ModelAndView order_before(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("mypage/order_before");
        return mv;
    }


//    취소/반품 내역
    @RequestMapping("order_cancel")
    public ModelAndView order_cancel(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("mypage/order_cancel");
        return mv;
    }


    //    주문내역
    @GetMapping("order_details")
    public String order_details(Model model){
        List<OrderAddress> orderAddressesList = mypageService.getAllOrderAddress();
        model.addAttribute("orderAddressList", orderAddressesList);
        return "mypage/order_details";
    }


    //    주문내역 상세보기
    @GetMapping("order_details/{id}")
    public String order_details_id(Model model, @PathVariable Long id){
        Optional<OrderAddress> orderAddressesList = mypageService.getOrderList(id);
        model.addAttribute("orderAddressList", orderAddressesList);
        return "mypage/order_details";
    }


//    정기배송 관리
    @RequestMapping("order_regular_delivery")
    public ModelAndView order_regular_delivery(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("mypage/order_regular_delivery");
        return mv;
    }


//    재입고 알림 상품
    @RequestMapping("order_restocked")
    public ModelAndView order_restocked(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("mypage/order_restocked");
        return mv;
    }

}
