package com.mustache.hospital.controller;

import com.mustache.hospital.domain.HospitalDto;
import com.mustache.hospital.domain.HospitalEntity;
import com.mustache.hospital.repository.HospitalRepository;
import com.mustache.hospital.service.HospitalService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
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
    private final HospitalService hospitalService;

    public HospitalController(HospitalRepository hospitalRepository, HospitalService hospitalService) {
        this.hospitalRepository = hospitalRepository;
        this.hospitalService = hospitalService;
    }

    @GetMapping(value="/list")
    public String list(Model model, @PageableDefault(size = 10, sort = "id") Pageable pageable){
        //List<HospitalEntity> hospitalEntities = hospitalRepository.findAll();
        //model.addAttribute("hospitals", hospitalEntities);
        model.addAttribute("hospitals", hospitalService.getBoardList(pageable));
        model.addAttribute("previous", pageable.previousOrFirst().getPageNumber());
        model.addAttribute("next", pageable.next().getPageNumber());
        return "list";
    }

}
