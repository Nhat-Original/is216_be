package com.github.nhatoriginal.spring.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.github.nhatoriginal.spring.dto.address.AddressDto;
import com.github.nhatoriginal.spring.dto.address.SaveAddressDto;
import com.github.nhatoriginal.spring.model.Address;
import com.github.nhatoriginal.spring.model.User;
import com.github.nhatoriginal.spring.repository.AddressRepository;
import com.github.nhatoriginal.spring.repository.UserRepository;

@Service
public class AddressService {
  @Autowired
  private AddressRepository addressRepository;

  @Autowired
  private UserRepository userRepository;

  public List<AddressDto> findByUserId(UUID userId) {
    userRepository.findById(userId).orElseThrow(
        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

    return addressRepository.findByUserId(userId).stream().map(
        address -> new AddressDto(address.getId(), address.getProvince(), address.getDistrict(), address.getWard(),
            address.getDetail()))
        .toList();
  }

  public Address save(SaveAddressDto saveAddressDto) {
    User user = userRepository.findById(saveAddressDto.getUserId())
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

    Address address = new Address();
    address.setDistrict(saveAddressDto.getDistrict());
    address.setProvince(saveAddressDto.getProvince());
    address.setWard(saveAddressDto.getWard());
    address.setDetail(saveAddressDto.getDetail());

    Address savedAddress = addressRepository.save(address);
    user.getAddresses().add(savedAddress);
    userRepository.save(user);

    return savedAddress;
  }

  public Address delete(UUID id) {
    return addressRepository.findById(id)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Address not found"));
  }
}
