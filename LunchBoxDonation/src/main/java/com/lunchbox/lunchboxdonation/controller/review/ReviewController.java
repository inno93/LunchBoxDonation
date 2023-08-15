package com.lunchbox.lunchboxdonation.controller.review;

import com.lunchbox.lunchboxdonation.entity.Review.Review;
import com.lunchbox.lunchboxdonation.service.Review.ReviewService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
@Slf4j
@RequestMapping("mainPage")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

//    @GetMapping("read")
//    public String read(@RequestParam Long lunchboxId, Model model){
//        // 화면 이동 용
//        if (lunchboxId == null) {
//            // 더미 데이터를
//            lunchboxId = 123L;
//        }
//        List<Review> reviews = reviewService.findByLunchboxId(lunchboxId);
//        System.out.println("도시락 번호 : " + lunchboxId);
//        model.addAttribute("reviews", reviews);
//        return "/mainPage/read";
//    }
    @GetMapping("read")
    public void read(){

    }

    @PostMapping("read")
    public RedirectView write(@RequestParam Long lunchboxId, @RequestParam String reviewContent) {
        reviewService.write(lunchboxId, reviewContent);
        return new RedirectView("/mainPage/read");
    }

}
