package com.github.nhatoriginal.spring.dto.review;

import java.util.UUID;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class SaveReviewDto {
  @NotNull
  @Min(1)
  @Max(5)
  private int rating;

  @NotBlank
  private String comment;

  @NotNull
  private UUID userId;

  @NotNull
  private UUID menuItemId;
}
