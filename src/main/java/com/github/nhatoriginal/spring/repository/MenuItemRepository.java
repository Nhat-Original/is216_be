package com.github.nhatoriginal.spring.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.github.nhatoriginal.spring.model.MenuItem;
import java.util.Optional;
import java.util.List;
import java.util.UUID;

@Repository
public interface MenuItemRepository extends JpaRepository<MenuItem, UUID> {

  List<MenuItem> findByNameContainingAndIsDeleteFalse(String name);
  List<MenuItem> findAllByMenuIdAndNameContainingAndIsDeleteFalse(UUID menuId, String name);
  @Modifying
  @Transactional
  @Query(nativeQuery = true, value = "UPDATE menu_items SET is_delete = :b WHERE id = :id")
  void updateIsDelete(UUID id, boolean b);
  @Query(nativeQuery = true, value = "SELECT * FROM menu_items WHERE id = :id AND is_delete = false")
  Optional<MenuItem> findByIdAndIsDeleteFalse(UUID id);
  @Query(nativeQuery = true, value = "SELECT * FROM menu_items WHERE is_delete = false")
  List<MenuItem> findAllWithIsDeleteFalse();
}
