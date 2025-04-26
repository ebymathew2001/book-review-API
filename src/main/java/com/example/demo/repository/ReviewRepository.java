package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Entity.Review;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
	  List<Review> findByBookId(Long bookId);
	  List<Review> findByUserId(Long userId);

}
