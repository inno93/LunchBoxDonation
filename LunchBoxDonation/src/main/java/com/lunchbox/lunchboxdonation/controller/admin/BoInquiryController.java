package com.lunchbox.lunchboxdonation.controller.admin;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequestMapping("admin/inquiry")
public class BoInquiryController {

    //    1:1문의 상세 보기
    @GetMapping("detail")
    public void inquiryDetail(){

    }

    //    1:1문의 목록
    @GetMapping("list")
    public void inquiryList(){

    }
}
