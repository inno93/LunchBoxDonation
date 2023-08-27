package com.lunchbox.lunchboxdonation.service.likes;

import com.lunchbox.lunchboxdonation.entity.Order.Order;
import com.lunchbox.lunchboxdonation.entity.member.Member;
import com.lunchbox.lunchboxdonation.entity.special.Likes;
import com.lunchbox.lunchboxdonation.exception.CustomException;
import com.lunchbox.lunchboxdonation.exception.Error;
import com.lunchbox.lunchboxdonation.repository.likes.LikesRepository;
import com.lunchbox.lunchboxdonation.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class LikesService {

    private final LikesRepository likesRepository;
    private final MemberRepository memberRepository;

    public void addToCart(Member member, Order order) {
        Optional<Member> members = memberRepository.findByEmail(member.getMemberEmail());
        if(members.isEmpty()){
            throw new CustomException(Error.NOT_FOUND_MEMBER);
        }
        Likes likes = Likes.of(member, order);
        likesRepository.save(likes);
        log.info("likes = "+likes);
    }

    public void cancel(Member member, Order order) {
        Optional<Member> members = memberRepository.findByEmail(member.getMemberEmail());
        if (members.isEmpty()) {
            throw new CustomException(Error.NOT_FOUND_MEMBER);
        }
        Likes likes = Likes.of(member, order);
        likesRepository.delete(likes);
    }


//    @Transactional(readOnly = true)
//    public ResponseEntity<LikesResponseDTO> getLikes(Member member) {
//        List<Likes> mypageList = likesRepository.findByMemberId(member.getId());
//        List<OrderResponseDTO> orderResponseDTOS = new ArrayList<>();
//        for (Likes likes: mypageList) {
//            orderResponseDTOS.add(OrderResponseDTO.of(likes.getMember()));
//            log.info("mypageList = "+mypageList);
//        }
//        log.info("orderResponseDTOS = "+orderResponseDTOS);
//        return ResponseEntity.ok((LikesResponseDTO) orderResponseDTOS);
//    }
//
//
//    public ApiResponseDto<LikesResponseDTO> likes(Long orderId,String email) {
//        Order order = getOrder(orderId);
//        Optional<Likes> likes = likesRepository.findByOrderAndMember(order, Member.builder().memberEmail(email).build());
//        if(likes.isEmpty()){
//            Likes found = Likes.of(Member.builder().build(), Order.builder().build());
//            likesRepository.save(found);
//            log.info("found = " + found);
//        }
//        return ResponseUtils.ok(LikesResponseDTO.of(true,"담았습니다.", HttpStatus.OK));
//    }
//
//    public ApiResponseDto<LikesResponseDTO> cancel(Long orderId, String email) {
//        Order order = getOrder(orderId);
//        Optional<Likes> likes = likesRepository.findByOrderAndMember(order, Member.builder().memberEmail(email).build());
//        if(likes.isEmpty()){
//            likesRepository.delete(likes.get());
//        }
//        log.info("likes = " + likes);
//        return ResponseUtils.ok(LikesResponseDTO.of(false,"삭제했습니다.", HttpStatus.OK));
//    }
//
//
//    private Order getOrder(Long postId) {
//        Order order = orderRepository.findById(postId).orElseThrow(
//                () -> new IllegalArgumentException("게시물을 찾을 수 없습니다.")
//        );
//        return order;
//    }




}
