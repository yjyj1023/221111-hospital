package com.mustache.hospital.domain.dto;

import com.mustache.hospital.domain.Hospital;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@ToString
@AllArgsConstructor
public class HospitalRequest {
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
    private String hospitalName;
    private String businessTypeName;
    private int healthcareProviderCount;
    private int patientRoomCount;
    private int totalNumberOfBeds;
    private float totalAreaSize;

    public Hospital toEntity() {
        return new Hospital(
                this.id, this.openServiceName, this.openLocalGovernmentCode,
                this.managementNumber, this.licenseDate, this.businessStatus,
                this.businessStatusCode, this.phone, this.fullAddress, this.roadNameAddress,
                this.hospitalName, this.businessTypeName, this.healthcareProviderCount,
                this.patientRoomCount, this.totalNumberOfBeds, this.totalAreaSize);
    }
}
