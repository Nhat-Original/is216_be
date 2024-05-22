package com.github.nhatoriginal.spring.repository;
import com.github.nhatoriginal.spring.model.Eatery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface EateryRepository extends JpaRepository<Eatery, UUID>{

}
