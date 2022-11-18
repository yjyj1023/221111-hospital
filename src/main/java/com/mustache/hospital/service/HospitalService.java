package com.mustache.hospital.service;

import com.mustache.hospital.domain.HospitalEntity;
import com.mustache.hospital.domain.HospitalResponse;
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
        Optional<HospitalEntity> optHospital = hospitalRepository.findById(id); // Entity
        HospitalEntity hospital = optHospital.get();
        HospitalResponse hospitalResponse = HospitalEntity.of(hospital); // DTO
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
    public Page<HospitalEntity> getBoardList(Pageable pageable) {

        return hospitalRepository.findAll(pageable);

    }

    @Transactional
    public List<HospitalEntity> getSearchBoardList(String keyword, Pageable pageable) {

        return hospitalRepository.findByRoadNameAddressContaining(keyword, pageable);

    }
}
