package com.lunchbox.lunchboxdonation.controller.oauth;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpSession;

@Controller
public class OAuthController {
    @GetMapping("/")
    public RedirectView main(HttpSession session, RedirectAttributes attributes){

        return new RedirectView("/mainPage/foodmain");

    }
}
