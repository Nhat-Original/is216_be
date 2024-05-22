package com.github.nhatoriginal.spring.service;

import com.github.nhatoriginal.spring.model.Menu;
import com.github.nhatoriginal.spring.repository.MenuRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class MenuService {
    private final MenuRepository menuRepository;
    public MenuService( MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    public Menu findById(UUID id) {
        return menuRepository.findById(id).orElse(null);

    }
}
