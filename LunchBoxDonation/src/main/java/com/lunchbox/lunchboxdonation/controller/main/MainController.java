package com.lunchbox.lunchboxdonation.controller.main;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@Slf4j
@RequestMapping("mainPage")
public class MainController {

    @GetMapping("foodmain")
    public void main() {

    }
}