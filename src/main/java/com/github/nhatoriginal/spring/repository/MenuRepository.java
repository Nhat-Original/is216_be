package com.github.nhatoriginal.spring.repository;

import com.github.nhatoriginal.spring.model.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface MenuRepository extends JpaRepository<Menu, UUID> {
    @Query("SELECT m FROM Menu m JOIN FETCH m.menuItems WHERE m.id = :id")
    Menu findByIdAndFetchMenuItemsEagerly(@Param("id") UUID id);
}
