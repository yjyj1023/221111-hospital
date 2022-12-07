package com.mustache.hospital.controller;


import com.mustache.hospital.domain.Visit;
import com.mustache.hospital.domain.dto.VisitCreateRequest;
import com.mustache.hospital.domain.dto.VisitResponse;
import com.mustache.hospital.service.VisitService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/visits")
@RequiredArgsConstructor
@Slf4j
public class VisitController {
    private final VisitService visitService;

    @PostMapping
    public ResponseEntity<String> createVisitLog(@RequestBody VisitCreateRequest visitCreateRequest, Authentication authentication) {
        String userName = authentication.getName();
        log.info("userName:{}", userName);
        visitService.createVisit(visitCreateRequest, userName);
        return ResponseEntity.ok().body("방문 기록이 등록 되었습니다.");
    }

    @GetMapping("")
    public ResponseEntity<List<VisitResponse>> list(Pageable pageable) {
        return ResponseEntity.ok().body(visitService.findAllByPage(pageable));
    }

}
