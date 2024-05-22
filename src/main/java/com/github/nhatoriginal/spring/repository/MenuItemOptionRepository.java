package com.github.nhatoriginal.spring.repository;

import org.springframework.stereotype.Repository;
import com.github.nhatoriginal.spring.model.MenuItemOption;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

@Repository
public interface MenuItemOptionRepository extends JpaRepository<MenuItemOption, UUID> {
    List<MenuItemOption> findAllByMenuItemId(UUID menuItemId);
}
