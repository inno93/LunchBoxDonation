package com.lunchbox.lunchboxdonation.controller.admin;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequestMapping("admin")
public class BoLunchBoxController {

    //    기부
    @GetMapping("donation")
    public void donation(){

    }
    // 도시락
    //목록
    @GetMapping("lunchboxList")
    public void lunchBoxList(){}
//    등록
    @GetMapping("lunchboxWrite")
    public void lunchBoxWrite(){

    }
//    상세 보기
    @GetMapping("lunchboxDetail")
    public void lunchBoxDetail(){

    }

    // 이달의 특가
}
