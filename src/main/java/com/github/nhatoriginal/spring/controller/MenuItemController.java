package com.github.nhatoriginal.spring.controller;

import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.github.nhatoriginal.spring.constant.Endpoint;
import com.github.nhatoriginal.spring.dto.menuItem.MenuItemDetailDto;
import com.github.nhatoriginal.spring.dto.menuItem.MenuItemDto;
import com.github.nhatoriginal.spring.service.MenuItemService;

@RestController
@RequestMapping(Endpoint.MenuItem.BASE)
public class MenuItemController {
  private final MenuItemService menuItemService;
  public MenuItemController(MenuItemService menuItemService) {
    this.menuItemService = menuItemService;
  }
  @GetMapping(Endpoint.MenuItem.GET_ALL)
  public List<MenuItemDto> findAll(@RequestParam(name = "name", required = false) String name) {
    return menuItemService.findAll(name);
  }
  @PostMapping(Endpoint.MenuItem.CREATE)
    public MenuItemDetailDto create(@RequestBody MenuItemDetailDto menuItemDto) {
        System.out.println(menuItemDto);
        return menuItemService.create(menuItemDto);
    }

//  @GetMapping(Endpoint.MenuItem.GET_ONE)
//  public MenuItemDetailDto findById(@PathVariable UUID id) {
//    return menuItemService.findById(id);
//  }
//  @GetMapping("")
//  public  List<MenuItemDto> findAllByOwner() {
//    return menuItemService.findAll(null);
//  }
}
