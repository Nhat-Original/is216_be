package com.github.nhatoriginal.spring.controller;

import com.github.nhatoriginal.spring.constant.Endpoint;
import com.github.nhatoriginal.spring.model.Menu;
import com.github.nhatoriginal.spring.service.MenuService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping(Endpoint.Menu.BASE)
public class MenuController {
    private final MenuService menuService;
    MenuController( MenuService menuService) {
        this.menuService = menuService;
    }
    @GetMapping(Endpoint.Menu.GET_ONE)
    public ResponseEntity<Menu> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(menuService.findById(id));
    }


}
