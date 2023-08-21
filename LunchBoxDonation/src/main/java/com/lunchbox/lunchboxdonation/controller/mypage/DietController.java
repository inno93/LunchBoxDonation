package com.lunchbox.lunchboxdonation.controller.mypage;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@Slf4j
public class DietController {



//    식단상담 목록
    @RequestMapping("diet_list")
    public ModelAndView diet_list(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("mypage/diet_list");
        return mv;
    }


//    식단상담 등록
    @RequestMapping("diet_registration")
    public ModelAndView diet_registration(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("mypage/diet_registration");
        return mv;
    }
}
