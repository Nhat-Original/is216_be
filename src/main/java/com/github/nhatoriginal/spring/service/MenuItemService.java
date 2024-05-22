package com.github.nhatoriginal.spring.service;

import com.github.nhatoriginal.spring.model.Menu;
import com.github.nhatoriginal.spring.model.MenuItemOption;
import com.github.nhatoriginal.spring.repository.MenuItemOptionRepository;
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
  private  final MenuItemOptionRepository menuItemOptionRepository;
  public MenuItemService(MenuItemRepository menuItemRepository, MenuRepository menuRepository, MenuItemOptionRepository menuItemOptionRepository) {
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
            () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Món ăn không tồn tại"));

    return createMenuItemDetailDto(menuItem);
  }

  public List<MenuItemDetailDto> findAllByMenuId(UUID menuId) {
    List<MenuItem> menuItems = menuItemRepository.findAllByMenuId(menuId);

    return menuItems.stream().map(this::createMenuItemDetailDto).collect(Collectors.toList());
  }

  private MenuItemDetailDto createMenuItemDetailDto(MenuItem menuItem) {
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
            new MenuItemDetailDto.Eatery(menuItem.getMenu().getId(), menuItem.getMenu().getEatery().getName(),
                    new MenuItemDetailDto.Eatery.Address(menuItem.getMenu().getEatery().getAddress().getProvince(),
                            menuItem.getMenu().getEatery().getAddress().getDistrict(),
                            menuItem.getMenu().getEatery().getAddress().getWard(),
                            menuItem.getMenu().getEatery().getAddress().getDetail())), menuItem.getMenu().getId().toString());
  }
  public MenuItemDetailDto create(MenuItemDetailDto menuItemDto) {
    MenuItem menuItem = new MenuItem();
    setMenuItemProperties(menuItem, menuItemDto);
    menuItem = menuItemRepository.save(menuItem);
    setMenuItemOptions(menuItem, menuItemDto.getMenuItemOptions());
    menuItemRepository.save(menuItem);
    return menuItemDto;
  }

  public MenuItemDetailDto update(UUID id, MenuItemDetailDto menuItemDto) {
    MenuItem menuItem = menuItemRepository.findById(id).orElseThrow(
            () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Món ăn không tồn tại"));
    setMenuItemProperties(menuItem, menuItemDto);
    menuItem = menuItemRepository.save(menuItem);
    updateMenuItemOptions(menuItem, menuItemDto.getMenuItemOptions());
    menuItemRepository.save(menuItem);
    return menuItemDto;
  }
  public String delete(UUID id) {
    MenuItem menuItem = menuItemRepository.findById(id).orElseThrow(
            () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Món ăn không tồn tại"));
    List<MenuItemOption> menuItems = menuItemOptionRepository.findAllByMenuItemId(id);
    menuItemOptionRepository.deleteAll(menuItems);
    menuItemRepository.delete(menuItem);
    return "Xóa thành công";

  }
  private void setMenuItemProperties(MenuItem menuItem, MenuItemDetailDto menuItemDto) {
    menuItem.setName(menuItemDto.getName());
    menuItem.setDescription(menuItemDto.getDescription());
    menuItem.setImageUrl(menuItemDto.getImageUrl());
    Menu menu = menuRepository.findById(UUID.fromString(menuItemDto.getMenu_id()))
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Menu not found"));
    menuItem.setMenu(menu);
  }

  private void setMenuItemOptions(MenuItem menuItem, List<MenuItemDetailDto.MenuItemOption> menuItemOptionDtos) {
    List<MenuItemOption> menuItemOptions = menuItemOptionDtos.stream()
            .map(menuItemOptionDto -> {
              MenuItemOption menuItemOption = new MenuItemOption();
              menuItemOption.setSize(menuItemOptionDto.getSize());
              menuItemOption.setPrice(menuItemOptionDto.getPrice());
              menuItemOption.setMenuItem(menuItem);
              return menuItemOptionRepository.save(menuItemOption);
            }).collect(Collectors.toList());
    menuItem.setMenuItemOptions(menuItemOptions);
  }

  private void updateMenuItemOptions(MenuItem menuItem, List<MenuItemDetailDto.MenuItemOption> menuItemOptionDtos) {
    List<MenuItemOption> existingOptions = menuItem.getMenuItemOptions();
    List<MenuItemOption> newOptions = menuItemOptionDtos.stream()
            .map(menuItemOptionDto -> {
              MenuItemOption menuItemOption;
              if (menuItemOptionDto.getId() == null) {
                menuItemOption= new MenuItemOption();
              } else {
               menuItemOption = menuItemOptionRepository.findById(menuItemOptionDto.getId())
                        .orElse(new MenuItemOption());
              }
              menuItemOption.setSize(menuItemOptionDto.getSize());
              menuItemOption.setPrice(menuItemOptionDto.getPrice());
              menuItemOption.setMenuItem(menuItem);
              return menuItemOptionRepository.save(menuItemOption);
            }).toList();
    existingOptions.stream()
            .filter(existingOption -> newOptions.stream().noneMatch(newOption -> newOption.getId().equals(existingOption.getId())))
            .forEach(menuItemOptionRepository::delete);
    existingOptions.clear();
    existingOptions.addAll(newOptions);
    menuItem.setMenuItemOptions(existingOptions);
  }

}
