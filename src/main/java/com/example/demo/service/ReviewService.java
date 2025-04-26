package com.example.demo.service;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.Book;
import com.example.demo.Entity.Review;
import com.example.demo.Entity.User;
import com.example.demo.repository.BookRepository;
import com.example.demo.repository.ReviewRepository;
import com.example.demo.repository.UserRepository;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private UserRepository userRepository;

    public Review createReview(Long bookId, Long userId, Review review) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found"));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        review.setBook(book);
        review.setUser(user);
        return reviewRepository.save(review);
    }

    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }

    public Review getReviewById(Long id) {
        return reviewRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Review not found"));
    }

    public Review updateReview(Long id, Review updatedReview) {
        Review existingReview = reviewRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Review not found"));
        existingReview.setRating(updatedReview.getRating());
        existingReview.setComment(updatedReview.getComment());
        return reviewRepository.save(existingReview);
    }

    public void deleteReview(Long id) {
        reviewRepository.deleteById(id);
    }

    public List<Review> getReviewsByBook(Long bookId) {
        return reviewRepository.findByBookId(bookId);
    }

    public List<Review> getReviewsByUser(Long userId) {
        return reviewRepository.findByUserId(userId);
    }
    
    public Double getAverageRating(Long bookId) {
	    List<Review> reviews = reviewRepository.findByBookId(bookId);
	    if (reviews.isEmpty()) {
	        return 0.0; // No reviews yet
	}
	double sum = 0;
	for (Review review : reviews) {
	    sum += review.getRating();
	}
	return sum / reviews.size(); // Return average rating
	}
	 
}
