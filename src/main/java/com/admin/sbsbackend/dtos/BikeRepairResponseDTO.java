package com.admin.sbsbackend.dtos;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BikeRepairResponseDTO {
    private String id;
    private String name;
    private String bikeModel;
    private String serviceType;
    private String contactNo;
    private String address;
    private String preferredTime;
    private String serviceStatus;
    private String mechanic;
}
