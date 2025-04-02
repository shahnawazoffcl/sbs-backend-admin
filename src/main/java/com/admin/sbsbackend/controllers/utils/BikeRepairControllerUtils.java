package com.admin.sbsbackend.controllers.utils;

import com.admin.sbsbackend.dtos.BikeRepairResponseDTO;
import com.admin.sbsbackend.dtos.MechanicAssignDTO;
import com.admin.sbsbackend.models.BikeRepair;
import com.admin.sbsbackend.models.Mechanic;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BikeRepairControllerUtils {

    public static BikeRepairResponseDTO mapToBikeRepairResponseDTO(BikeRepair bikeRepair){
        BikeRepairResponseDTO bikeRepairResponseDTO =  new BikeRepairResponseDTO();
        bikeRepairResponseDTO.setId(bikeRepair.getId().toString());
        bikeRepairResponseDTO.setBikeModel(bikeRepair.getBikeModel());
        bikeRepairResponseDTO.setAddress(bikeRepair.getAddress());
        bikeRepairResponseDTO.setName(bikeRepair.getName());
        bikeRepairResponseDTO.setContactNo(bikeRepair.getContactNo());
        bikeRepairResponseDTO.setPreferredTime(bikeRepair.getPreferredTime());
        bikeRepairResponseDTO.setServiceType(bikeRepair.getServiceType());
        bikeRepairResponseDTO.setServiceStatus(bikeRepair.getServiceStatus());
        bikeRepairResponseDTO.setMechanic(bikeRepair.getMechanic());
        return bikeRepairResponseDTO;
    }

    public static List<String> getMechanics(List<Mechanic> mechanics){
        List<String> mechanicNames = new ArrayList<>();
        for(Mechanic mechanic: mechanics){
            mechanicNames.add(mechanic.getName());
        }
        return mechanicNames;
    }

    public static void createBikeRepair(BikeRepair bikeRepair,MechanicAssignDTO updateDTO) {
        bikeRepair.setBikeModel(updateDTO.getBikeModel());
        bikeRepair.setAddress(updateDTO.getAddress());
        bikeRepair.setName(updateDTO.getName());
        bikeRepair.setContactNo(updateDTO.getContactNo());
        bikeRepair.setPreferredTime(updateDTO.getPreferredTime());
        bikeRepair.setServiceType(updateDTO.getServiceType());
        bikeRepair.setServiceStatus(updateDTO.getServiceStatus());
        bikeRepair.setMechanic(updateDTO.getMechanic());
    }
}
