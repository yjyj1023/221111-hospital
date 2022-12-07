package com.mustache.hospital.repository;

import com.mustache.hospital.domain.Hospital;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HospitalRepository extends JpaRepository<Hospital, Integer> {
    List<Hospital> findByBusinessTypeNameIn(List<String> businessTypes);
    List<Hospital> findByRoadNameAddressContaining(String keyword); //포함
    List<Hospital> findByHospitalNameStartsWith(String keyword); //시작
    List<Hospital> findByHospitalNameEndsWith(String keyword); //끝남
    List<Hospital> findByPatientRoomCountBetween(int var1, int var2);// Between

    List<Hospital> findByPatientRoomCountBetweenOrderByPatientRoomCountDesc(int var1, int var2);// Order By (desc는 내림차순)

    List<Hospital> findByRoadNameAddressContaining(String str, Pageable pageable);
}
