package com.lunchbox.lunchboxdonation.service.Review;

import com.lunchbox.lunchboxdonation.entity.Review.Review;

import java.util.List;

public interface ReviewService {
    List<Review> findByLunchboxId(Long lunchboxId);
    Review write(Long lunchboxId, String reviewContent);

    Review update(Long lunchboxId, String reviewContent);

    public void deleteReview(Long Id);
}