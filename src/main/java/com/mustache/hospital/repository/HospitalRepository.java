package com.mustache.hospital.repository;

import com.mustache.hospital.domain.HospitalEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HospitalRepository extends JpaRepository<HospitalEntity, Integer> {
}
