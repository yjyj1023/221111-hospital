package com.mustache.hospital.repository;

import com.mustache.hospital.domain.HospitalEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HospitalRepository extends JpaRepository<HospitalEntity, Integer> {
    List<HospitalEntity> findByBusinessTypeNameIn(List<String> businessTypes);
    List<HospitalEntity> findByRoadNameAddressContaining(String keyword); //포함
    List<HospitalEntity> findByHospitalNameStartsWith(String keyword); //시작
    List<HospitalEntity> findByHospitalNameEndsWith(String keyword); //끝남
    List<HospitalEntity> findByPatientRoomCountBetween(int var1, int var2);// Between

    List<HospitalEntity> findByPatientRoomCountBetweenOrderByPatientRoomCountDesc(int var1, int var2);// Order By (desc는 내림차순)

    List<HospitalEntity> findByRoadNameAddressContaining(String str, Pageable pageable);
}
