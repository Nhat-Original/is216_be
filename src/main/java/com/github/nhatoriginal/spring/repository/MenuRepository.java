package com.github.nhatoriginal.spring.repository;

import com.github.nhatoriginal.spring.model.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface MenuRepository extends JpaRepository<Menu, UUID> {
}
