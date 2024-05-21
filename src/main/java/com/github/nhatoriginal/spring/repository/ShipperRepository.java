package com.github.nhatoriginal.spring.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.github.nhatoriginal.spring.model.Shipper;

import java.util.UUID;

@Repository
public interface ShipperRepository extends JpaRepository<Shipper, UUID> {
}
