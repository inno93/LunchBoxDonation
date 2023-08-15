package com.lunchbox.lunchboxdonation.controller.admin;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequestMapping("admin/notice")
public class BoNoticeController {

//      공지사항 등록
    @GetMapping("nociteWrite")
    public void nociteWrite(){}

//    공지사항 목록
    @GetMapping("noticeList")
    public void noticeList(){

    }
//    공지사항 상세보기
    @GetMapping("noticeDetail")
    public void noticeDetail(){

    }
}
