package com.lunchbox.lunchboxdonation.controller.mypage;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@Slf4j
public class MypageController {

    @RequestMapping("mypage")
    public ModelAndView mypage(){
        ModelAndView mv = new ModelAndView();

        mv.setViewName("mypage/mypage"); // html 파일 경로
        log.info(String.valueOf(mv));
        return mv;
    }

}
