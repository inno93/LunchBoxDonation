package com.lunchbox.lunchboxdonation.controller.admin;

import com.lunchbox.lunchboxdonation.service.lunchbox.LunchBoxService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("admin")
public class BoLunchBoxController {
    private final LunchBoxService lunchBoxService;

    //    기부
    @GetMapping("donation")
    public void donation(){

    }
    // 도시락
    //목록
    @GetMapping("lunchboxList")
    public void lunchBoxList(){}
//    등록
    @GetMapping("lunchboxWrite")
    public void lunchBoxWrite(){

    }
//    상세 보기
    @GetMapping("lunchboxDetail")
    public void lunchBoxDetail(){
//        ModelAndView mv = new ModelAndView();
////        mv.setViewName("admin/lunchbox/lunchboxDetail");
//        mv.setViewName("admin/lunchboxDetail");
//        return mv;
    }

    // 이달의 특가
}
