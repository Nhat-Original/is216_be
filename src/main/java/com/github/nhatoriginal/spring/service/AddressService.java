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

  private final AddressRepository addressRepository;
  private final UserRepository userRepository;

  public AddressService(AddressRepository addressRepository, UserRepository userRepository) {
    this.addressRepository = addressRepository;
    this.userRepository = userRepository;
  }
  public List<AddressDto> findByUserId(UUID userId) {
    userRepository.findById(userId).orElseThrow(
        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Người dùng không tồn tại"));

    return addressRepository.findByUserId(userId).stream().map(
        address -> new AddressDto(address.getId(), address.getProvince(), address.getDistrict(), address.getWard(),
            address.getDetail()))
        .toList();
  }

  public Address save(SaveAddressDto body) {
    User user = userRepository.findById(body.getUserId())
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Người dùng không tồn tại"));

    Address address = new Address();
    address.setDistrict(body.getDistrict());
    address.setProvince(body.getProvince());
    address.setWard(body.getWard());
    address.setDetail(body.getDetail());

    Address savedAddress = addressRepository.save(address);
    user.getAddresses().add(savedAddress);
    userRepository.save(user);

    return savedAddress;
  }

  public void delete(UUID id) {
    addressRepository.findById(id)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Địa chỉ không tồn tại"));

    userRepository.deleteUserAddressesByAddressId(id);
    addressRepository.deleteById(id);
  }
}
