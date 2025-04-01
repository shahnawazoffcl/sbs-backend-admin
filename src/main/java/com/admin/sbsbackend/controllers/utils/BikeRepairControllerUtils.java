package com.admin.sbsbackend.controllers.utils;

import com.admin.sbsbackend.dtos.BikeRepairResponseDTO;
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
        return bikeRepairResponseDTO;
    }

    public static List<String> getMechanics(List<Mechanic> mechanics){
        List<String> mechanicNames = new ArrayList<>();
        for(Mechanic mechanic: mechanics){
            mechanicNames.add(mechanic.getName());
        }
        return mechanicNames;
    }

}
