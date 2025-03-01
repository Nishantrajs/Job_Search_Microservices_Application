package com.example.reviewms.review.impl;

import com.example.reviewms.review.Review;
import com.example.reviewms.review.ReviewRepository;
import com.example.reviewms.review.ReviewService;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ReviewServiceImpl implements ReviewService {

    ReviewRepository reviewRepository;
    private Long reviewID = 1L;

    public ReviewServiceImpl(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Override
    public List<Review> getAllReviews(Long companyId) {

        List<Review> reviews = reviewRepository.findByCompanyId(companyId);
        return reviews;
    }

    @Override
    public boolean addReview(Long companyId, Review review){

        if(companyId!=null && review != null)
        {
            review.setCompanyId(companyId);
            review.setId(reviewID++);
            reviewRepository.save(review);
            return true;
        }

        return false;
    }

    @Override
    public Review getCompanyReviewByReviewId(Long reviewId) {

       return reviewRepository.findById(reviewId).orElse(null);
    }

    @Override
    public boolean updateReview(Long reviewId, Review updatedReview) {

        Review review = reviewRepository.findById(reviewId).orElse(null);

       if(reviewId != null)
       {
            review.setTitle(updatedReview.getTitle());
            review.setDescription(updatedReview.getDescription());
            review.setRating(updatedReview.getRating());
            review.setCompanyId(updatedReview.getCompanyId());

            reviewRepository.save(review);
            return true;
       }

       return false;
    }

    @Override
    public boolean deleteReview(Long reviewId) {

        Review review = reviewRepository.findById(reviewId).orElse(null);

        if(review != null )
        {
            reviewRepository.delete(review);

            return true;
        }

        return false;
    }
}
