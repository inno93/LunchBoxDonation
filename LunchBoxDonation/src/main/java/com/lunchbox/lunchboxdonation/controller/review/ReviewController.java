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
//    public void read(@RequestParam Long lunchboxId, Model model){
//        List<Review> reviews = reviewService.findByLunchboxId(lunchboxId);
//        model.addAttribute("reviews", reviews);
//    }
    @GetMapping("read")
    public String read(@RequestParam Long lunchboxId, Model model) {
        List<Review> reviews = reviewService.findByLunchboxId(lunchboxId);
        model.addAttribute("reviews", reviews);
        System.out.println("read Test");
        return "mainPage/read";
    }
//    @PostMapping("read")
//    public RedirectView write(@RequestParam Long lunchboxId, @RequestParam String reviewContent) {
//        reviewService.write(lunchboxId, reviewContent);
//        return new RedirectView("/mainPage/read");
//    }

    @PostMapping("read")
    public RedirectView write(@RequestParam("lunchboxId") Long lunchboxId, @RequestParam String reviewContent) {
        reviewService.write(lunchboxId, reviewContent);
        System.out.println("read Test2");
        return new RedirectView("/mainPage/read?lunchboxId=" + lunchboxId);
    }

//    @PostMapping("update")
//    public RedirectView updateReview(@RequestParam Long lunchboxId, @RequestParam String newReviewContent) {
//        reviewService.update(lunchboxId, newReviewContent);
//        return new RedirectView("/mainPage/read?lunchboxId=" + lunchboxId);
//    }

    @PostMapping("delete")
    public RedirectView deleteReview(@RequestParam Long Id, @RequestParam Long lunchboxId) {
        reviewService.deleteReview(Id);
        return new RedirectView("/mainPage/read?lunchboxId=" + lunchboxId);
    }
}
