package com.lunchbox.lunchboxdonation.controller.mypage;

import com.lunchbox.lunchboxdonation.domain.likes.LikesResponseDTO;
import com.lunchbox.lunchboxdonation.exception.ApiResponseDto;
import com.lunchbox.lunchboxdonation.service.likes.LikseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/read/{order_id}")
public class LikesController {

    private final LikseService likseService;

    @PostMapping("/likes")
    public ApiResponseDto<LikesResponseDTO> likes(@PathVariable Long order_id,String email){
        return likseService.likes(order_id,email);
    }

    @PostMapping("/cancel")
    public ApiResponseDto<LikesResponseDTO> cancel(@PathVariable Long order_id,String email){
        return likseService.cancel(order_id,email);
    }
}
