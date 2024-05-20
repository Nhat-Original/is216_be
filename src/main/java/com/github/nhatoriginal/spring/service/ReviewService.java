package com.github.nhatoriginal.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.github.nhatoriginal.spring.dto.review.SaveReviewDto;
import com.github.nhatoriginal.spring.model.MenuItem;
import com.github.nhatoriginal.spring.model.Review;
import com.github.nhatoriginal.spring.model.User;
import com.github.nhatoriginal.spring.repository.MenuItemRepository;
import com.github.nhatoriginal.spring.repository.ReviewRepository;
import com.github.nhatoriginal.spring.repository.UserRepository;
import org.springframework.http.HttpStatus;

@Service
public class ReviewService {
  @Autowired
  private ReviewRepository reviewRepository;
  @Autowired
  private UserRepository userRepository;
  @Autowired
  private MenuItemRepository menuItemRepository;

  public Review save(SaveReviewDto saveReviewDto) {
    User user = userRepository.findById(saveReviewDto.getUserId())
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Người dùng không tồn tại"));

    MenuItem menuItem = menuItemRepository.findById(saveReviewDto.getMenuItemId())
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Món ăn không tồn tại"));

    Review review = new Review();
    review.setRating(saveReviewDto.getRating());
    review.setComment(saveReviewDto.getComment());
    review.setUser(user);
    review.setMenuItem(menuItem);

    return reviewRepository.save(review);
  }
}