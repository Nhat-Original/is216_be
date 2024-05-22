package com.github.nhatoriginal.spring.repository;

import com.github.nhatoriginal.spring.model.User;

import jakarta.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
  User findByEmail(String email);

  @Modifying
  @Transactional
  @Query(value = "DELETE FROM user_addresses WHERE address_id = :addressId", nativeQuery = true)
  void deleteUserAddressesByAddressId(UUID addressId);
}
