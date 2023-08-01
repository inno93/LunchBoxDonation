package com.lunchbox.lunchboxdonation.controller.admin;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequestMapping("admin")
public class BoNoticeController {

//      공지사항 등록
    @GetMapping("nociteWrite")
    public void nociteWrite(){}

}
