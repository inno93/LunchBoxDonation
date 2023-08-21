package com.lunchbox.lunchboxdonation.controller.mypage;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@Slf4j
public class ActivityController {




//    관심브랜드
    @RequestMapping("activity_brand")
    public ModelAndView activity_brand(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("mypage/activity_brand");
        return mv;
    }


//    상품문의내역
    @RequestMapping("activity_inquiry_details")
    public ModelAndView activity_inquiry_details(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("mypage/activity_inquiry_details");
        return mv;
    }


//    1:1문의
    @RequestMapping("activity_interview")
    public ModelAndView activity_interview(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("mypage/activity_interview");
        return mv;
    }


//    찜하기
    @RequestMapping("activity_likes")
    public ModelAndView activity_likes(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("mypage/activity_likes");
        return mv;
    }


//    최근 본 상품
    @RequestMapping("activity_recently")
    public ModelAndView activity_recently(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("mypage/activity_recently");
        return mv;
    }


//    상품후기
    @RequestMapping("activity_review")
    public ModelAndView activity_review(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("mypage/activity_review");
        return mv;
    }
}
