package com.lunchbox.lunchboxdonation.controller.admin;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@Slf4j
@RequestMapping("admin/donation")
@RequiredArgsConstructor
public class BoDonationController {


    @GetMapping("donationList")
    public ModelAndView donationList(){

        ModelAndView mv = new ModelAndView();


        mv.setViewName("/admin/donation/donationList");
        return mv;
    }
}
