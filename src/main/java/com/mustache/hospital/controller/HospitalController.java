package com.mustache.hospital.controller;

import com.mustache.hospital.domain.HospitalDto;
import com.mustache.hospital.domain.HospitalEntity;
import com.mustache.hospital.repository.HospitalRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/hospital")
@Slf4j
public class HospitalController {

    private final HospitalRepository hospitalRepository;

    public HospitalController(HospitalRepository hospitalRepository) {
        this.hospitalRepository = hospitalRepository;
    }

    @GetMapping(value="/list")
    public String list(Model model){
        List<HospitalEntity> hospitalEntities = hospitalRepository.findAll();
        model.addAttribute("hospitals", hospitalEntities);
        return "list";
    }

}
