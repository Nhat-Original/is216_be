package com.github.nhatoriginal.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.github.nhatoriginal.spring.constant.Endpoint;
import com.github.nhatoriginal.spring.dto.review.SaveReviewDto;
import com.github.nhatoriginal.spring.model.Review;
import com.github.nhatoriginal.spring.service.ReviewService;

@RestController
@RequestMapping(Endpoint.Review.BASE)
public class ReviewController {
  @Autowired
  private ReviewService reviewService;

  @PostMapping(Endpoint.Review.CREATE)
  public ResponseEntity<String> save(@Validated @RequestBody SaveReviewDto body) {
    Review review = reviewService.save(body);

    if (review == null) {
      return new ResponseEntity<String>("Lưu đánh giá thất bại", HttpStatus.BAD_REQUEST);
    }

    return new ResponseEntity<String>("Lưu đánh giá thành công", HttpStatus.CREATED);
  }
}
