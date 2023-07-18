package com.lunchbox.lunchboxdonation.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@Slf4j
public class TestController {

    @RequestMapping("")
    public ModelAndView test(){
        ModelAndView mv = new ModelAndView();

        log.info("test");

        mv.setViewName("test"); // html 파일 경로

        return mv;
    }
}
