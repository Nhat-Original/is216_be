package com.github.nhatoriginal.spring.controller;

import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.github.nhatoriginal.spring.constant.Endpoint;
import com.github.nhatoriginal.spring.dto.menuItem.MenuItemDetailDto;
import com.github.nhatoriginal.spring.dto.menuItem.MenuItemDto;
import com.github.nhatoriginal.spring.service.MenuItemService;

@RestController
@RequestMapping(Endpoint.MenuItem.BASE)
public class MenuItemController {
  @Autowired
  private MenuItemService menuItemService;

  @GetMapping(Endpoint.MenuItem.GET_ALL)
  public List<MenuItemDto> findAll(@RequestParam("name") String name) {
    return menuItemService.findAll(name);
  }

  @GetMapping(Endpoint.MenuItem.GET_ONE)
  public MenuItemDetailDto findById(@PathVariable UUID id) {
    return menuItemService.findById(id);
  }
}
