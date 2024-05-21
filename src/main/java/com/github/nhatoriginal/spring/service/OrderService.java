package com.github.nhatoriginal.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.github.nhatoriginal.spring.dto.order.OrderDto;
import com.github.nhatoriginal.spring.dto.order.SaveOrderDto;
import com.github.nhatoriginal.spring.model.Address;
import com.github.nhatoriginal.spring.model.DeliveryStatus;
import com.github.nhatoriginal.spring.model.Eatery;
import com.github.nhatoriginal.spring.model.MenuItemOption;
import com.github.nhatoriginal.spring.model.Order;
import com.github.nhatoriginal.spring.model.OrderDetail;
import com.github.nhatoriginal.spring.model.Shipper;
import com.github.nhatoriginal.spring.model.User;
import com.github.nhatoriginal.spring.repository.AddressRepository;
import com.github.nhatoriginal.spring.repository.EateryRepository;
import com.github.nhatoriginal.spring.repository.MenuItemOptionRepository;
import com.github.nhatoriginal.spring.repository.OrderDetailRepository;
import com.github.nhatoriginal.spring.repository.OrderRepository;
import com.github.nhatoriginal.spring.repository.ShipperRepository;
import com.github.nhatoriginal.spring.repository.UserRepository;

import java.util.List;
import java.util.Random;
import java.util.UUID;
import org.springframework.http.HttpStatus;

@Service
public class OrderService {
  @Autowired
  private OrderRepository orderRepository;
  @Autowired
  private OrderDetailRepository orderDetailRepository;
  @Autowired
  private UserRepository userRepository;
  @Autowired
  private AddressRepository addressRepository;
  @Autowired
  private EateryRepository eateryRepository;
  @Autowired
  private ShipperRepository shipperRepository;
  @Autowired
  private MenuItemOptionRepository menuItemOptionRepository;

  public List<OrderDto> findByUserId(UUID userId) {
    userRepository.findById(userId).orElseThrow(
        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Người dùng không tồn tại"));

    return orderRepository.findByUserId(userId).stream().map(
        order -> new OrderDto(order.getId(), order.getOrderDate(), order.getTotalPrice(), order.getNote(),
            order.getPaymentMethod(), order.getDeliveryStatus(),
            new OrderDto.Eatery(order.getEatery().getId(), order.getEatery().getName())))
        .toList();
  }

  public void save(SaveOrderDto body) {
    Random randomGenerator = new Random();

    Address address = addressRepository.findById(body.getAddressId())
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Địa chỉ không tồn tại"));
    Eatery eatery = eateryRepository.findById(body.getEateryId())
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cửa hàng không tồn tại"));
    User user = userRepository.findById(body.getUserId())
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Người dùng không tồn tại"));
    List<Shipper> shippers = shipperRepository.findAll();

    Order order = new Order();
    order.setNote(body.getNote());
    order.setOrderDate(body.getOrderDate());
    order.setTotalPrice(body.getTotalPrice());
    order.setPaymentMethod(body.getPaymentMethod());
    order.setAddress(address);
    order.setEatery(eatery);
    order.setUser(user);
    order.setDeliveryStatus(randomGenerator.nextBoolean() ? DeliveryStatus.SUCCESS
        : DeliveryStatus.FAIL); // Pick a random delivery status
    order.setShipper(shippers.get(randomGenerator.nextInt(shippers.size()))); // Pick a random shipper

    orderRepository.save(order);

    body.getOrderDetails().forEach(_orderDetail -> {
      MenuItemOption menuItemOption = menuItemOptionRepository.findById(_orderDetail.getMenuItemOptionId())
          .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tùy chọn món ăn không tồn tại"));

      OrderDetail orderDetail = new OrderDetail();
      orderDetail.setOrder(order);
      orderDetail.setQuantity(_orderDetail.getQuantity());
      orderDetail.setMenuItemOption(menuItemOption);
      orderDetailRepository.save(orderDetail);
    });
  }
}
