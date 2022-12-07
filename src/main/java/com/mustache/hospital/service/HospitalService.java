package com.mustache.hospital.service;

import com.mustache.hospital.domain.Hospital;
import com.mustache.hospital.domain.dto.HospitalResponse;
import com.mustache.hospital.repository.HospitalRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class HospitalService {

    private final HospitalRepository hospitalRepository;

    public HospitalService(HospitalRepository hospitalRepository) {
        this.hospitalRepository = hospitalRepository;
    }

    public HospitalResponse getHospital(Integer id){
        Optional<Hospital> optHospital = hospitalRepository.findById(id); // Entity
        Hospital hospital = optHospital.get();
        HospitalResponse hospitalResponse = Hospital.of(hospital); // DTO
        if (hospital.getBusinessStatusCode() == 13) {
            hospitalResponse.setBusinessStatusName("영업중");
        } else if (hospital.getBusinessStatusCode() == 3) {
            hospitalResponse.setBusinessStatusName("폐업");
        } else {
            hospitalResponse.setBusinessStatusName(String.valueOf(hospital.getBusinessStatusCode()));
        }
        return hospitalResponse;
    }

    @Transactional
    public Page<Hospital> getBoardList(Pageable pageable) {

        return hospitalRepository.findAll(pageable);

    }

    @Transactional
    public List<Hospital> getSearchBoardList(String keyword, Pageable pageable) {

        return hospitalRepository.findByRoadNameAddressContaining(keyword, pageable);

    }
}
