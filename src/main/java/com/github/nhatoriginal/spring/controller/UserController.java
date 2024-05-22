package com.github.nhatoriginal.spring.controller;
import com.github.nhatoriginal.spring.constant.Endpoint;
import com.github.nhatoriginal.spring.model.User;
import com.github.nhatoriginal.spring.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
@RestController
@RequestMapping(Endpoint.User.BASE)
public class UserController {
private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }
    @GetMapping(Endpoint.User.GET_ONE)
    public ResponseEntity<User> getUserById(@PathVariable String id) {
       return ResponseEntity.ok(this.service.getUserById(id));
    }

}
