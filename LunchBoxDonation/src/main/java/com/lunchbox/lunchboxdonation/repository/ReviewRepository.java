package com.lunchbox.lunchboxdonation.repository;

import com.lunchbox.lunchboxdonation.entity.Review.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review,Long> {
    @Query("select r from Review r join  r.member join fetch r.lunchbox")
    List<Review> findAllReviews();
}
