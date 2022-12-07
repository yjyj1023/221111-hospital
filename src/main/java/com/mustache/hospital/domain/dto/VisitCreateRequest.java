package com.mustache.hospital.domain.dto;

import com.mustache.hospital.domain.Hospital;
import com.mustache.hospital.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class VisitCreateRequest {
    private Integer hospitalId;
    private Long userId;
    private String disease;
    private float amount;
}
