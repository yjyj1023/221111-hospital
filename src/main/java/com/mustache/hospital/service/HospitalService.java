package com.mustache.hospital.service;

import com.mustache.hospital.domain.HospitalEntity;
import com.mustache.hospital.repository.HospitalRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class HospitalService {

    private final HospitalRepository hospitalRepository;

    public HospitalService(HospitalRepository hospitalRepository) {
        this.hospitalRepository = hospitalRepository;
    }

    @Transactional
    public Page<HospitalEntity> getBoardList(Pageable pageable) {

        return hospitalRepository.findAll(pageable);

    }
}
