package com.mustache.hospital.repository;

import com.mustache.hospital.domain.HospitalEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HospitalRepository extends JpaRepository<HospitalEntity, Integer> {
    List<HospitalEntity> findByBusinessTypeNameIn(List<String> businessTypes);
}
