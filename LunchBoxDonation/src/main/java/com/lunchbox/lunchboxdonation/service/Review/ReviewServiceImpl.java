package com.lunchbox.lunchboxdonation.service.Review;

import com.lunchbox.lunchboxdonation.entity.Lunchbox.LunchBox;
import com.lunchbox.lunchboxdonation.entity.Review.Review;
import com.lunchbox.lunchboxdonation.repository.lunchbox.LunchBoxRepository;
import com.lunchbox.lunchboxdonation.repository.review.ReviewRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService{

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private LunchBoxRepository lunchBoxRepository;

    @Override
    public List<Review> findByLunchboxId(Long lunchboxId) {
        return reviewRepository.findByLunchboxId(lunchboxId);
    }

    @Override
    public Review write(Long lunchboxId, String reviewContent) {
        LunchBox lunchBox = lunchBoxRepository.findById(lunchboxId).orElse(null);
        if(lunchBox != null){
            Review review = new Review();
            review.setReviewContent(reviewContent);
            review.setLunchbox(lunchBox);
            // Member 정보도 설정
            // review.setMember(member);
            return reviewRepository.save(review);
        }
        return null;
    }

    @Override
    public Review update(Long lunchboxId, String reviewContent) {
        Review review = reviewRepository.findById(lunchboxId).orElse(null);
        if (review != null) {
            review.setReviewContent(reviewContent);
            return reviewRepository.save(review);
        }
        return null;
    }

    @Override
    public void deleteReview(Long Id) {
        reviewRepository.deleteById(Id);
    }
}
