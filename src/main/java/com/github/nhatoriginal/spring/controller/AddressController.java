package com.github.nhatoriginal.spring.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.github.nhatoriginal.spring.constant.Endpoint;
import com.github.nhatoriginal.spring.dto.address.AddressDto;
import com.github.nhatoriginal.spring.dto.address.SaveAddressDto;
import com.github.nhatoriginal.spring.model.Address;
import com.github.nhatoriginal.spring.service.AddressService;

@RestController
@RequestMapping(Endpoint.Address.BASE)
public class AddressController {
  @Autowired
  private AddressService addressService;

  @PostMapping(Endpoint.Address.CREATE)
  public ResponseEntity<String> save(@Validated @RequestBody SaveAddressDto saveAddressDto) {
    Address address = addressService.save(saveAddressDto);

    if (address == null) {
      return ResponseEntity.badRequest().body("Failed to save address");
    }

    return ResponseEntity.ok("Saved address successfully");
  }

  @GetMapping(Endpoint.Address.GET_BY_USER_ID)
  public List<AddressDto> findByUserId(@PathVariable UUID userId) {
    return addressService.findByUserId(userId);
  }

  @DeleteMapping(Endpoint.Address.DELETE)
  public ResponseEntity<String> delete(@PathVariable UUID id) {
    addressService.delete(id);
    return ResponseEntity.ok("Deleted address successfully");
  }
}
