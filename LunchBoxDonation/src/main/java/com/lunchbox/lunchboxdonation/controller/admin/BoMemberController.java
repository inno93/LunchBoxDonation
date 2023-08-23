package com.lunchbox.lunchboxdonation.controller.admin;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@Slf4j
@RequestMapping("admin/member")
public class BoMemberController {

    //    회원 목록
    @GetMapping("memberList")
    public ModelAndView memberList(){

        ModelAndView mv = new ModelAndView();


        mv.setViewName("/admin/memberList");
        return mv;

    }

}
