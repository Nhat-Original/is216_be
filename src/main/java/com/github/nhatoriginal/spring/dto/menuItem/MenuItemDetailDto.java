package com.github.nhatoriginal.spring.dto.menuItem;

import java.util.List;

import com.github.nhatoriginal.spring.model.Size;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MenuItemDetailDto {
  private String id;
  private String name;
  private String description;
  private String imageUrl;
  private List<MenuItemOption> menuItemOptions;
  private List<Review> reviews;
  private Eatery eatery;

  @Data
  @AllArgsConstructor
  public static class Eatery {
    private String id;
    private String name;
  }

  @Data
  @AllArgsConstructor
  public static class MenuItemOption {
    private String id;
    private Size size;
    private double price;
  }

  @Data
  @AllArgsConstructor
  public static class Review {
    private String id;
    private int rating;
    private String comment;
    private User user;

    @Data
    @AllArgsConstructor
    public static class User {
      private String id;
      private String fullName;
    }
  }
}
