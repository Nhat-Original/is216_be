package com.github.nhatoriginal.spring.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;
import com.github.nhatoriginal.spring.model.MenuItem;
import java.util.UUID;

@Repository
public interface MenuItemRepository extends CrudRepository<MenuItem, UUID> {
}
