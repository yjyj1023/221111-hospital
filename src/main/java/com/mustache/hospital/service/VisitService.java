package com.mustache.hospital.service;

import com.mustache.hospital.domain.Article;
import com.mustache.hospital.domain.Hospital;
import com.mustache.hospital.domain.User;
import com.mustache.hospital.domain.Visit;
import com.mustache.hospital.domain.dto.VisitCreateRequest;
import com.mustache.hospital.domain.dto.VisitResponse;
import com.mustache.hospital.exception.AppException;
import com.mustache.hospital.exception.ErrorCode;
import com.mustache.hospital.repository.HospitalRepository;
import com.mustache.hospital.repository.UserRepository;
import com.mustache.hospital.repository.VisitRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class VisitService {

    private final VisitRepository visitRepository;
    private final HospitalRepository hospitalRepository;
    private final UserRepository userRepository;

    public void createVisit(VisitCreateRequest dto, String userName) {

        // hospital이 없을 때 등록불가
        Hospital hospital = hospitalRepository.findById(dto.getHospitalId())
                .orElseThrow(() -> new AppException(ErrorCode.NOT_FOUNDED, String.format("hospitalId:%s 가 없습니다.", dto.getHospitalId())));

        // user가 없을 때 등록불가
        User user = userRepository.findByUserName(userName)
                .orElseThrow(() -> new AppException(ErrorCode.USERNAME_NOT_FOUNDED, String.format("%s user가 없습니다.", userName)));

        Visit visit = Visit.builder()
                .user(user)
                .hospital(hospital)
                .disease(dto.getDisease())
                .amount(dto.getAmount())
                .build();
        visitRepository.save(visit);
    }

    public List<VisitResponse> findAllByPage(Pageable pageable) {
        Page<Visit> visits = visitRepository.findAll(pageable);

        // Visits -> VisitResponse
        return visits.stream()
                .map(Visit::toResponse)
                .collect(Collectors.toList());
    }
}
