package com.github.nhatoriginal.spring.service;

import com.github.nhatoriginal.spring.model.Menu;
import com.github.nhatoriginal.spring.model.MenuItemOption;
import com.github.nhatoriginal.spring.repository.MenuItemOptionsRepository;
import com.github.nhatoriginal.spring.repository.MenuRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.github.nhatoriginal.spring.dto.menuItem.MenuItemDetailDto;
import com.github.nhatoriginal.spring.dto.menuItem.MenuItemDto;
import com.github.nhatoriginal.spring.model.MenuItem;
import com.github.nhatoriginal.spring.repository.MenuItemRepository;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;

@Service
public class MenuItemService {

  private final MenuItemRepository menuItemRepository;
  private  final MenuRepository menuRepository;
  private  final MenuItemOptionsRepository menuItemOptionRepository;
  public MenuItemService(MenuItemRepository menuItemRepository, MenuRepository menuRepository, MenuItemOptionsRepository menuItemOptionRepository) {
    this.menuItemRepository = menuItemRepository;
    this.menuRepository = menuRepository;
    this.menuItemOptionRepository = menuItemOptionRepository;
  }
  public List<MenuItemDto> findAll(String name) {
    List<MenuItem> menuItems;

    if (name == null || name.isEmpty()) {
      menuItems = menuItemRepository.findAll();
    } else {
      menuItems = menuItemRepository.findByNameContaining(name);
    }

    return menuItems.stream().map(
        menuItem -> new MenuItemDto(menuItem.getId(), menuItem.getName(), menuItem.getImageUrl())).toList();
  }

  public MenuItemDetailDto findById(UUID id) {
    MenuItem menuItem = menuItemRepository.findById(id).orElseThrow(
        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Menu item not found"));

      return new MenuItemDetailDto(
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
        new MenuItemDetailDto.Eatery(menuItem.getMenu().getId(), menuItem.getMenu().getEatery().getName()), "");
  }
  public MenuItemDetailDto create(MenuItemDetailDto menuItemDto) {
    MenuItem menuItem = new MenuItem();
    menuItem.setName(menuItemDto.getName());
    menuItem.setDescription(menuItemDto.getDescription());
    menuItem.setImageUrl(menuItemDto.getImageUrl());
    Menu menu = menuRepository.findById(UUID.fromString(menuItemDto.getMenu_id()))
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Menu not found"));
    menuItem.setMenu(menu);
    menuItem = menuItemRepository.save(menuItem);
    MenuItem finalMenuItem = menuItem;
    List<MenuItemOption> menuItemOptions = menuItemDto.getMenuItemOptions().stream()
            .map(menuItemOptionDto -> {
              MenuItemOption menuItemOption = new MenuItemOption();
              menuItemOption.setSize(menuItemOptionDto.getSize());
              menuItemOption.setPrice(menuItemOptionDto.getPrice());
              menuItemOption.setMenuItem(finalMenuItem);
              return menuItemOptionRepository.save(menuItemOption);
            }).collect(Collectors.toList());
    menuItem.setMenuItemOptions(menuItemOptions);
    menuItemRepository.save(menuItem);
    return menuItemDto;
  }
}
