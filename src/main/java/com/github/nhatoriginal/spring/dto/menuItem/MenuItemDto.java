package com.github.nhatoriginal.spring.dto.menuItem;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MenuItemDto {
  private UUID id;
  private String name;
  private String imageUrl;
}
