package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.Entity.Review;
import com.example.demo.service.ReviewService;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    // 1. Create a review
    @PostMapping("/book/{bookId}/user/{userId}")
    public Review createReview(@PathVariable Long bookId,
                               @PathVariable Long userId,
                               @RequestBody Review review) {
        return reviewService.createReview(bookId, userId, review);
    }

    // 2. Get all reviews
    @GetMapping
    public List<Review> getAllReviews() {
        return reviewService.getAllReviews();
    }

    // 3. Get review by ID
    @GetMapping("/{id}")
    public Review getReviewById(@PathVariable Long id) {
        return reviewService.getReviewById(id);
    }

    // 4. Update a review
    @PutMapping("/{id}")
    public Review updateReview(@PathVariable Long id,
                               @RequestBody Review review) {
        return reviewService.updateReview(id, review);
    }

    // 5. Delete a review
    @DeleteMapping("/{id}")
    public String deleteReview(@PathVariable Long id) {
        reviewService.deleteReview(id);
        return "Review deleted successfully";
    }

    // 6. Get reviews by book ID
    @GetMapping("/book/{bookId}")
    public List<Review> getReviewsByBook(@PathVariable Long bookId) {
        return reviewService.getReviewsByBook(bookId);
    }

    // 7. Get reviews by user ID
    @GetMapping("/user/{userId}")
    public List<Review> getReviewsByUser(@PathVariable Long userId) {
        return reviewService.getReviewsByUser(userId);
    }
    
    @GetMapping("/books/{bookId}/average-rating")
    public ResponseEntity<Double> getAverageRating(@PathVariable Long bookId) {
        Double avgRating = reviewService.getAverageRating(bookId);
        return ResponseEntity.ok(avgRating);
    }
    
    
    
    
    
}
