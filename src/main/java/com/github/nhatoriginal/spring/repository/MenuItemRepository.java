package com.github.nhatoriginal.spring.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.github.nhatoriginal.spring.model.MenuItem;

import java.util.List;
import java.util.UUID;

@Repository
public interface MenuItemRepository extends JpaRepository<MenuItem, UUID> {
  List<MenuItem> findByNameContaining(String name);

  @Query(nativeQuery = true, value = "SELECT * FROM menu_items WHERE menu_id = :menuId")
  List<MenuItem> findAllByMenuId(UUID menuId);
}
