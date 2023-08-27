package com.lunchbox.lunchboxdonation.service.likes;

import com.lunchbox.lunchboxdonation.domain.likes.LikesResponseDTO;
import com.lunchbox.lunchboxdonation.entity.Order.Order;
import com.lunchbox.lunchboxdonation.entity.member.Member;
import com.lunchbox.lunchboxdonation.entity.special.Likes;
import com.lunchbox.lunchboxdonation.exception.ApiResponseDto;
import com.lunchbox.lunchboxdonation.exception.ResponseUtils;
import com.lunchbox.lunchboxdonation.repository.OrderRepository;
import com.lunchbox.lunchboxdonation.repository.likes.LikesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class LikseService {

    private final LikesRepository likesRepository;
    private final OrderRepository orderRepository;

    public ApiResponseDto<LikesResponseDTO> likes(Long orderId,String email) {
        Order order = getOrder(orderId);
        Optional<Likes> likes = likesRepository.findByOrderAndMember(order, Member.builder().memberEmail(email).build());
        if(likes.isEmpty()){
            Likes found = Likes.of(Member.builder().build(), Order.builder().build());
            likesRepository.save(found);
        }
        return ResponseUtils.ok(LikesResponseDTO.of(true,"담았습니다.", HttpStatus.OK));
    }

    public ApiResponseDto<LikesResponseDTO> cancel(Long orderId, String email) {
        Order order = getOrder(orderId);
        Optional<Likes> likes = likesRepository.findByOrderAndMember(order, Member.builder().memberEmail(email).build());
        if(likes.isEmpty()){
            likesRepository.delete(likes.get());
        }
        return ResponseUtils.ok(LikesResponseDTO.of(false,"삭제했습니다.", HttpStatus.OK));
    }


    private Order getOrder(Long postId) {
        Order order = orderRepository.findById(postId).orElseThrow(
                () -> new IllegalArgumentException("게시물을 찾을 수 없습니다.")
        );
        return order;
    }
}
