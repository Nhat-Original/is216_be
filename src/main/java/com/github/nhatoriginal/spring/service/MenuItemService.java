package com.github.nhatoriginal.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.github.nhatoriginal.spring.model.MenuItem;
import com.github.nhatoriginal.spring.repository.MenuItemRepository;
import java.util.UUID;
import org.springframework.http.HttpStatus;

@Service
public class MenuItemService {
  @Autowired
  private MenuItemRepository menuItemRepository;

  public Iterable<MenuItem> getMenuItemList() {
    return menuItemRepository.findAll();
  }

  public MenuItem getMenuItemById(UUID id) {
    return menuItemRepository.findById(id)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "MenuItem not found"));
  }
}
