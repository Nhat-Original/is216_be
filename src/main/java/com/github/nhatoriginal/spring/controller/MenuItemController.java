package com.github.nhatoriginal.spring.controller;

import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.github.nhatoriginal.spring.constant.Endpoint;
import com.github.nhatoriginal.spring.model.MenuItem;
import com.github.nhatoriginal.spring.service.MenuItemService;

@RestController
@RequestMapping(Endpoint.MenuItem.BASE)
public class MenuItemController {
  @Autowired
  private MenuItemService menuItemService;

  @GetMapping(Endpoint.MenuItem.GET_ALL)
  public List<MenuItem> getMenuItemList() {
    return menuItemService.getMenuItemList();
  }

  @GetMapping(Endpoint.MenuItem.GET_ONE)
  public MenuItem getMenuItemById(@PathVariable UUID id) {
    return menuItemService.getMenuItemById(id);
  }
}
