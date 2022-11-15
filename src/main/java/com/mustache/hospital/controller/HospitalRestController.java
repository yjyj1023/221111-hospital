package com.mustache.hospital.controller;

import com.mustache.hospital.domain.HospitalEntity;
import com.mustache.hospital.domain.HospitalResponse;
import com.mustache.hospital.repository.HospitalRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/hospitals")
public class HospitalRestController {

    private final HospitalRepository hospitalRepository;

    public HospitalRestController(HospitalRepository hospitalRepository) {
        this.hospitalRepository = hospitalRepository;
    }

    @GetMapping("/{id}")
    public ResponseEntity<HospitalResponse> get(@PathVariable Integer id) { // ResponseEntity도 DTO타입
        Optional<HospitalEntity> hospital = hospitalRepository.findById(id); // Entity
        HospitalResponse hospitalResponse = HospitalEntity.of(hospital.get()); // DTO
        return ResponseEntity.ok().body(hospitalResponse); // Return은 DTO로
    }

}