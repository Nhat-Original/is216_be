package com.github.nhatoriginal.spring.repository;
import com.github.nhatoriginal.spring.model.Eatery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface EateryRepository extends JpaRepository<Eatery, UUID>{
    @Query("SELECT e FROM Eatery e WHERE e.owner.id = :ownerId")
    List<Eatery> findAllByOwnerId(UUID ownerId);

}
