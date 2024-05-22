package com.github.nhatoriginal.spring.service;

import com.github.nhatoriginal.spring.constant.Endpoint;
import com.github.nhatoriginal.spring.model.Eatery;
import com.github.nhatoriginal.spring.repository.EateryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class EateryService {
    EateryRepository eateryRepository;
    EateryService( EateryRepository eateryRepository) {
        this.eateryRepository = eateryRepository;
    }
    public Eatery findById(UUID id) {

        return eateryRepository.findById(id).orElse(null);
    }
    public Eatery save(Eatery eatery) {
        return eateryRepository.save(eatery);
    }
}
