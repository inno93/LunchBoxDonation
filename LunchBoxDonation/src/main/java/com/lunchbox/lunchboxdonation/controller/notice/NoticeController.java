package com.lunchbox.lunchboxdonation.controller.notice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/notice")
@Slf4j
public class NoticeController {
    @RequestMapping("content")
    public ModelAndView content(){
        ModelAndView mv = new ModelAndView();

        mv.setViewName("/content.html"); // html 파일 이름 설정
        return mv;
    }

    @RequestMapping("list")
    public ModelAndView list(){
        ModelAndView mv = new ModelAndView();

        mv.setViewName("/list.html"); // html 파일 이름 설정
        return mv;
    }

    @RequestMapping("write")
    public ModelAndView write(){
        ModelAndView mv = new ModelAndView();

        mv.setViewName("/write.html"); // html 파일 이름 설정
        return mv;
    }

}
