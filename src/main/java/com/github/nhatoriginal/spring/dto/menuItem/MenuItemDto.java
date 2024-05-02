package com.github.nhatoriginal.spring.dto.menuItem;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MenuItemDto {
  private String id;
  private String name;
  private String imageUrl;
}
