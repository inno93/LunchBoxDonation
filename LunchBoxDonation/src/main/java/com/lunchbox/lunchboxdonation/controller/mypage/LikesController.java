package com.lunchbox.lunchboxdonation.controller.mypage;

import com.lunchbox.lunchboxdonation.entity.Order.Order;
import com.lunchbox.lunchboxdonation.entity.member.Member;
import com.lunchbox.lunchboxdonation.repository.OrderRepository;
import com.lunchbox.lunchboxdonation.service.likes.LikesService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("mainPage/read")
public class LikesController {

    private final LikesService likesService;
    private final OrderRepository orderRepository;


    @PostMapping("/{lunchboxId}/likes")
    public String addTocart(@RequestParam Long lunchboxId, Member member){
        Order order = getOrder(lunchboxId);
        likesService.addToCart(member,order);
        return "mainPage/read";
    }

    @PostMapping("/{lunchboxId}/cancel")
    public String addTocart(@RequestParam Long lunchboxId,Model model, Member member){
        Order order = getOrder(lunchboxId);
        likesService.cancel(member,order);
        return "mainPage/read";
    }

    private Order getOrder(Long lunchboxId) {
        Optional<Order> order = orderRepository.findById(lunchboxId);
        return order.orElseThrow(() -> new IllegalArgumentException("해당 주문을 찾을 수 없습니다."));
    }


}
