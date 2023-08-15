package com.lunchbox.lunchboxdonation.controller.admin;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequestMapping("admin")
public class BoLunchBoxController {

    //    후원
    @GetMapping("donation")
    public void donation(){

    }
    // 도시락
    // 기부
    // 이달의 특가
}
