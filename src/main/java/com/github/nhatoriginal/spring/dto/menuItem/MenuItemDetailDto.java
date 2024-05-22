package com.github.nhatoriginal.spring.dto.menuItem;

import java.util.List;
import java.util.UUID;

import com.github.nhatoriginal.spring.model.Size;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MenuItemDetailDto {
  private UUID id;
  private String name;
  private String description;
  private String imageUrl;
  private List<MenuItemOption> menuItemOptions;
  private List<Review> reviews;
  private Eatery eatery;
  private String menu_id;

  @Data
  @AllArgsConstructor
  public static class Eatery {
    private UUID id;
    private String name;
  }

  @Data
  @AllArgsConstructor
  public static class MenuItemOption {
    private UUID id;
    private Size size;
    private double price;
  }

  @Data
  @AllArgsConstructor
  public static class Review {
    private UUID id;
    private int rating;
    private String comment;
    private User user;

    @Data
    @AllArgsConstructor
    public static class User {
      private UUID id;
      private String fullName;
    }
  }
}
