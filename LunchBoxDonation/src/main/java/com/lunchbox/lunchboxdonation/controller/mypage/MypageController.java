package com.lunchbox.lunchboxdonation.controller.mypage;

import com.lunchbox.lunchboxdonation.entity.special.Likes;
import com.lunchbox.lunchboxdonation.entity.Order.OrderAddress;
import com.lunchbox.lunchboxdonation.entity.Review.Review;
import com.lunchbox.lunchboxdonation.service.mypage.MypageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;


@Controller
@Slf4j
@RequiredArgsConstructor
public class MypageController {

    private final MypageService mypageService;

    @GetMapping("/mypages")
    public String mypage(Model model){
//        최근 주문내역
        Page<OrderAddress> recentOrderAddressesPage = mypageService.getRecentOrderAddressesWithinThreeMonthsPageable(0, 3);
        model.addAttribute("recentOrderAddresses",recentOrderAddressesPage);
//        찜하기 내역
        List<Likes> likesList = mypageService.getAllLikes();
        model.addAttribute("likesList", likesList);
//        리뷰내역
        List<Review> reviewList = mypageService.getAllReview();
        model.addAttribute("reviews", reviewList);
        return "mypage/mypage";
    }



}
