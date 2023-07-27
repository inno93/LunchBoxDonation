package com.lunchbox.lunchboxdonation.controller.category;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequestMapping("mainPage")
public class CategoryController {

    @GetMapping("event")
    public void event(){

    }
    @GetMapping("deallOfTheMonth")
    public void deallOfTheMonth(){

    }
    @GetMapping("benefits")
    public void benefits(){

    }
}