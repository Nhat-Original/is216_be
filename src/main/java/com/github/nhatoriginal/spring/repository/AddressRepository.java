package com.github.nhatoriginal.spring.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.github.nhatoriginal.spring.model.Address;

import java.util.List;
import java.util.UUID;

@Repository
public interface AddressRepository extends JpaRepository<Address, UUID> {
  @Query(nativeQuery = true, value = "select * from addresses a join user_addresses ua on a.id = ua.address_id where ua.user_id = :userId")
  List<Address> findByUserId(UUID userId);
}
