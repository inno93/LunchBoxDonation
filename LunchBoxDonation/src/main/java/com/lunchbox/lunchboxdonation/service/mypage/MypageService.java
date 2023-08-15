package com.lunchbox.lunchboxdonation.service.mypage;


import com.lunchbox.lunchboxdonation.entity.special.Likes;
import com.lunchbox.lunchboxdonation.entity.Order.OrderAddress;
import com.lunchbox.lunchboxdonation.entity.Review.Review;
import com.lunchbox.lunchboxdonation.repository.likes.LikesRepository;
import com.lunchbox.lunchboxdonation.repository.orderaddress.OrderAddressRepository;
import com.lunchbox.lunchboxdonation.repository.review.ReviewRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class MypageService {
    
    private final OrderAddressRepository orderAddressRepository;
    private final LikesRepository likesRepository;
    private final ReviewRepository reviewRepository;


//  주문내역
    public Page<OrderAddress> getRecentOrderAddressesWithinThreeMonthsPageable(int pageNumber, int pageSize) {
        LocalDateTime threeMonthsAgo = LocalDateTime.now().minusMonths(3);
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return orderAddressRepository.findByModDateAfter(threeMonthsAgo, pageable);
    }


//  찜하기 내역
    public List<Likes> getAllLikes(){
        Pageable pageable = PageRequest.of(0, 3, Sort.by(Sort.Direction.DESC, "createdAt"));
        return likesRepository.findAllLikes(pageable);
    }


//  리뷰 내역
    public List<Review> getAllReview(){
        Pageable pageable = PageRequest.of(0, 3, Sort.by(Sort.Direction.DESC, "createdAt"));
        return reviewRepository.findAllReviews(pageable);
    }


////  1:1 문의 내역
//    public List<Inquiry> getAllInquiriesForMember(Member member) {
//        List<Member> members = memberRepository.getMemberById(member.getId());
//        if(members.isEmpty()){
//            throw new CustomException(Error.NOT_FOUND_POST);
//        }
//        return inquiryRepository.findAllByMember(member);
//    }
}
