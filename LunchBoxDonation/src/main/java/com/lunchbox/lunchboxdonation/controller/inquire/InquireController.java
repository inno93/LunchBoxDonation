package com.lunchbox.lunchboxdonation.controller.inquire;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@Slf4j
@RequestMapping("/mypage/inquire")
public class InquireController {

    @RequestMapping("list")
    public ModelAndView list(){
        ModelAndView mv = new ModelAndView();

        mv.setViewName("/mypage/inquire/list.html"); // html 파일 이름 설정
        return mv;
    }

    @RequestMapping("write")
    public ModelAndView write(){
        ModelAndView mv = new ModelAndView();

        mv.setViewName("/mypage/inquire/write.html"); // html 파일 이름 설정
        return mv;
    }
}
