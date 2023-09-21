package com.lunchbox.lunchboxdonation.controller.main;


import com.lunchbox.lunchboxdonation.entity.Lunchbox.LunchBox;
import com.lunchbox.lunchboxdonation.service.lunchbox.LunchBoxService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("mainPage")
public class MainController {
    private final LunchBoxService lunchBoxService;

    @GetMapping("foodmain")
    public ModelAndView main() {
        ModelAndView mv = new ModelAndView();

        //MD Pick 추천상품 가져오기
        List<LunchBox> mdList =  lunchBoxService.getMainList(8,0);
        mv.addObject("mdList", mdList);

        // 깜짝 타임세일
        List<LunchBox> timeSaleList = lunchBoxService.getMainList(6, 0);
        mv.addObject("timeSaleList", timeSaleList);


        //소스 매력에 퐁당
        List<LunchBox> theme1 = lunchBoxService.getMainList(5, 3);
        List<LunchBox> theme2 = lunchBoxService.getMainList(5, 3);
        List<LunchBox> theme3 = lunchBoxService.getMainList(5, 3);
        List<LunchBox> theme4 = lunchBoxService.getMainList(5, 3);
        List<LunchBox> theme5 = lunchBoxService.getMainList(5, 3);

        mv.addObject("theme1", theme1);
        mv.addObject("theme2", theme2);
        mv.addObject("theme3", theme3);
        mv.addObject("theme4", theme4);
        mv.addObject("theme5", theme5);

//        금주 BEST 신상품
        List<LunchBox> bestList = lunchBoxService.getMainList(8, 0);
//        log.info("{}", bestList.toString());
        mv.addObject("bestList",bestList);


        mv.setViewName("/mainPage/foodmain");




        return mv;
    }

}