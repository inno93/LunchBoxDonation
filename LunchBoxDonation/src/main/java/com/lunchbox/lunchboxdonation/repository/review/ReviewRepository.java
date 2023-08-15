package com.lunchbox.lunchboxdonation.repository.review;

import com.lunchbox.lunchboxdonation.entity.Review.Review;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review,Long> {
    @Query("SELECT r FROM Review r JOIN FETCH r.member JOIN FETCH r.lunchbox ORDER BY r.createdAt DESC")
    List<Review> findAllReviews(Pageable pageable);

}
