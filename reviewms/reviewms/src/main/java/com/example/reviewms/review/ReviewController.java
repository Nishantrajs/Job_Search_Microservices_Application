package com.example.reviewms.review;

 import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

    private ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping
    public ResponseEntity<List<Review>> getAllReviews(@RequestParam Long companyId)
    {
        return new ResponseEntity<>(reviewService.getAllReviews(companyId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> addReview(@RequestParam Long companyId, @RequestBody Review review)
    {
            boolean CheckIfAdded = reviewService.addReview(companyId, review);

            if(CheckIfAdded)
            return new ResponseEntity<>("REVIEW ADDED TO THE COMPANY SUCCESSFULLY !!", HttpStatus.OK);

            return new ResponseEntity<>("Error occurred while adding the Company !:", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/{reviewId}")
    public ResponseEntity<Review> getCompanyReviewByReviewId(@PathVariable Long reviewId)
    {
        Review review = reviewService.getCompanyReviewByReviewId(reviewId);

        if(review != null)
            return new ResponseEntity<>(review, HttpStatus.OK);

        return new ResponseEntity<>(review, HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{reviewId}")
    public ResponseEntity<String> updateReview( @PathVariable Long reviewId, @RequestBody Review review)
    {
        boolean updatedReview = reviewService.updateReview(reviewId,review);

        if(updatedReview)
            return new ResponseEntity<>("Review Updated Successfully !!", HttpStatus.OK);


        return new ResponseEntity<>("REVIEW RECORD NOT FOUND !!", HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{reviewId}")
    public ResponseEntity<String> deleteReview(@PathVariable Long reviewId)
    {
        boolean CheckIfDeleted = reviewService.deleteReview(reviewId);

        if(CheckIfDeleted)
            return new ResponseEntity<>("REVIEW DELTED SUCCESSFULLY !!", HttpStatus.OK);

        return new ResponseEntity<>("REVIEW NOT FOUND !!", HttpStatus.NOT_FOUND);
    }
}
