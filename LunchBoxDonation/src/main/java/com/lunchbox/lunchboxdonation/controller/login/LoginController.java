package com.lunchbox.lunchboxdonation.controller.login;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@Slf4j
@RequestMapping("login")
public class LoginController {

    @RequestMapping("login")
    public ModelAndView login(){
        ModelAndView mv = new ModelAndView();

        mv.setViewName("login/login"); // html 파일 경로

        return mv;
    }


    @RequestMapping("foundId")
    public ModelAndView foundId(){
        ModelAndView mv = new ModelAndView();

        mv.setViewName("login/foundId"); // html 파일 경로

        return mv;
    }

    @RequestMapping("foundPw")
    public ModelAndView foundPw(){
        ModelAndView mv = new ModelAndView();

        mv.setViewName("login/foundPw"); // html 파일 경로

        return mv;
    }

    @RequestMapping("join")
    public ModelAndView join(){
        ModelAndView mv = new ModelAndView();

        mv.setViewName("login/joinPage"); // html 파일 경로

        return mv;
    }

    @RequestMapping("reset")
    public ModelAndView resetPw(){
        ModelAndView mv = new ModelAndView();

        mv.setViewName("login/resetPw"); // html 파일 경로

        return mv;
    }

    @RequestMapping("successFound")
    public ModelAndView successFoundId(){
        ModelAndView mv = new ModelAndView();

        mv.setViewName("login/successFoundId"); // html 파일 경로

        return mv;
    }

    @RequestMapping("ranking")
    public ModelAndView ranking(){
        ModelAndView mv = new ModelAndView();

        mv.setViewName("login/rankingPage"); // html 파일 경로

        return mv;
    }

    @RequestMapping("kit")
    public ModelAndView kit(){
        ModelAndView mv = new ModelAndView();

        mv.setViewName("login/kit"); // html 파일 경로

        return mv;
    }

    @RequestMapping("order")
    public ModelAndView order(){
        ModelAndView mv = new ModelAndView();

        mv.setViewName("login/order"); // html 파일 경로

        return mv;
    }
}
