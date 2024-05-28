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

  private final ReviewRepository reviewRepository;

  private final UserRepository userRepository;

  private final MenuItemRepository menuItemRepository;
  public ReviewService(ReviewRepository reviewRepository, UserRepository userRepository, MenuItemRepository menuItemRepository) {
    this.reviewRepository = reviewRepository;
    this.userRepository = userRepository;
    this.menuItemRepository = menuItemRepository;
  }
  public Review save(SaveReviewDto body) {
    User user = userRepository.findById(body.getUserId())
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Người dùng không tồn tại"));

    MenuItem menuItem = menuItemRepository.findById(body.getMenuItemId())
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Món ăn không tồn tại"));

    Review review = new Review();
    review.setRating(body.getRating());
    review.setComment(body.getComment());
    review.setUser(user);
    review.setMenuItem(menuItem);

    return reviewRepository.save(review);
  }
}