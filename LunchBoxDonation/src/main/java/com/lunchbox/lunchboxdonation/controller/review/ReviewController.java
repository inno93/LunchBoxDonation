package com.lunchbox.lunchboxdonation.controller.review;

import com.lunchbox.lunchboxdonation.entity.Review.Review;
import com.lunchbox.lunchboxdonation.service.Review.ReviewService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
@Slf4j
@RequestMapping("mainPage")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @GetMapping("read")
    public String read(@RequestParam Long lunchboxId, Model model) {
        List<Review> reviews = reviewService.findByLunchboxId(lunchboxId);
        model.addAttribute("reviews", reviews);

        // lunchboxId에 해당하는 댓글 수 가져오기
        Long reviewCount = reviewService.getReviewCountByLunchboxId(lunchboxId);
        System.out.println(reviewCount); // 리뷰 개수 테스트 코드

        // 댓글 수를 모델에 추가하여 뷰에서 사용
        model.addAttribute("reviewCount", reviewCount);
        return "mainPage/read";
    }

    @PostMapping("read")
    public RedirectView write(@RequestParam("lunchboxId") Long lunchboxId, @RequestParam String reviewContent) {
        reviewService.write(lunchboxId, reviewContent);
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
