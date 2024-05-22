package com.github.nhatoriginal.spring.repository;

import com.github.nhatoriginal.spring.model.MenuItemOption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface MenuItemOptionsRepository extends JpaRepository<MenuItemOption, UUID> {
}
