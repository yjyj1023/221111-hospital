package com.mustache.hospital.domain;

import com.mustache.hospital.domain.dto.HospitalResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Table(name = "nation_wide_hospitals")
public class Hospital {
    @Id
    private int id;
    private String openServiceName;
    private int openLocalGovernmentCode;
    private String managementNumber;
    private LocalDateTime licenseDate;
    private int businessStatus;
    private int businessStatusCode;
    private String phone;
    private String fullAddress;
    private String roadNameAddress;
    @Column(name = "hospital_name")
    private String hospitalName;
    private String businessTypeName;
    private int healthcareProviderCount;
    private int patientRoomCount;
    private int totalNumberOfBeds;
    private float totalAreaSize;

    public Hospital(String openServiceName, int openLocalGovernmentCode, String managementNumber, LocalDateTime licenseDate, int businessStatus, int businessStatusCode, String phone, String fullAddress, String roadNameAddress, String hospitalName, String businessTypeName, int healthcareProviderCount, int patientRoomCount, int totalNumberOfBeds, float totalAreaSize) {
        this.openServiceName = openServiceName;
        this.openLocalGovernmentCode = openLocalGovernmentCode;
        this.managementNumber = managementNumber;
        this.licenseDate = licenseDate;
        this.businessStatus = businessStatus;
        this.businessStatusCode = businessStatusCode;
        this.phone = phone;
        this.fullAddress = fullAddress;
        this.roadNameAddress = roadNameAddress;
        this.hospitalName = hospitalName;
        this.businessTypeName = businessTypeName;
        this.healthcareProviderCount = healthcareProviderCount;
        this.patientRoomCount = patientRoomCount;
        this.totalNumberOfBeds = totalNumberOfBeds;
        this.totalAreaSize = totalAreaSize;
    }



    // HospitalEntity??? HospitalResponse Dto??? ??????????????? ??????
    public static HospitalResponse of(Hospital hospital) {
        return new HospitalResponse(hospital.getId(),
                hospital.getRoadNameAddress(),
                hospital.getHospitalName(),
                hospital.getPatientRoomCount(),
                hospital.getTotalNumberOfBeds(),
                hospital.getBusinessTypeName(),
                hospital.getTotalAreaSize());
    }
}
