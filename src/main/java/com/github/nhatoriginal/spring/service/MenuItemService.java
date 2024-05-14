package com.github.nhatoriginal.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.github.nhatoriginal.spring.dto.menuItem.MenuItemDetailDto;
import com.github.nhatoriginal.spring.dto.menuItem.MenuItemDto;
import com.github.nhatoriginal.spring.model.MenuItem;
import com.github.nhatoriginal.spring.repository.MenuItemRepository;

import java.util.List;
import java.util.UUID;
import org.springframework.http.HttpStatus;

@Service
public class MenuItemService {
  @Autowired
  private MenuItemRepository menuItemRepository;

  public List<MenuItemDto> findAll() {
    List<MenuItem> menuItems = menuItemRepository.findAll();

    return menuItems.stream().map(
        menuItem -> new MenuItemDto(menuItem.getId(), menuItem.getName(), menuItem.getImageUrl())).toList();
  }

  public MenuItemDetailDto findById(UUID id) {
    MenuItem menuItem = menuItemRepository.findById(id).orElseThrow(
        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Menu item not found"));

    MenuItemDetailDto menuItemDetailDto = new MenuItemDetailDto(
        menuItem.getId(),
        menuItem.getName(),
        menuItem.getDescription(),
        menuItem.getImageUrl(),
        menuItem.getMenuItemOptions().stream()
            .map(menuItemOption -> new MenuItemDetailDto.MenuItemOption(menuItemOption.getId(),
                menuItemOption.getSize(),
                menuItemOption.getPrice()))
            .toList(),
        menuItem.getReviews().stream().map(
            review -> new MenuItemDetailDto.Review(review.getId(), review.getRating(), review.getComment(),
                new MenuItemDetailDto.Review.User(review.getUser().getId(), review.getUser().getFullName())))
            .toList(),
        new MenuItemDetailDto.Eatery(menuItem.getMenu().getId(), menuItem.getMenu().getEatery().getName()));

    return menuItemDetailDto;
  }
}
