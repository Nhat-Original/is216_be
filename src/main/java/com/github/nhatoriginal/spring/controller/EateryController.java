package com.github.nhatoriginal.spring.controller;

import com.github.nhatoriginal.spring.constant.Endpoint;
import com.github.nhatoriginal.spring.dto.eatery.EateryDto;
import com.github.nhatoriginal.spring.model.Eatery;
import com.github.nhatoriginal.spring.repository.EateryRepository;
import com.github.nhatoriginal.spring.service.EateryService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(Endpoint.Eatery.BASE)
public class EateryController {
    EateryService eateryService;
    EateryController(EateryService eateryService) {
        this.eateryService = eateryService;
    }
    @GetMapping(Endpoint.Eatery.GET_ONE)
    public ResponseEntity<Eatery> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(eateryService.findById(id));
    }
    @PreAuthorize("hasRole('ROLE_OWNER')")
    @GetMapping(Endpoint.Eatery.GET_ALL_ByOwnerId)
    public ResponseEntity<List<Eatery>> findAll(@PathVariable UUID ownerId) {
        return ResponseEntity.ok(eateryService.findAllByOwnerId(ownerId));
    }

}
