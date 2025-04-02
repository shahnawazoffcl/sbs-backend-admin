package com.admin.sbsbackend.controllers;

import com.admin.sbsbackend.controllers.utils.AdminUserControllerUtils;
import com.admin.sbsbackend.controllers.utils.BikeRepairControllerUtils;
import com.admin.sbsbackend.dtos.BikeRepairResponseDTO;
import com.admin.sbsbackend.dtos.MechanicAssignDTO;
import com.admin.sbsbackend.models.BikeRepair;
import com.admin.sbsbackend.models.Mechanic;
import com.admin.sbsbackend.services.AdminUserService;
import com.admin.sbsbackend.services.BikeRepairService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/admin/service")
public class AdminBikeServiceController {

    private final BikeRepairService bikeRepairServiceService;
    private final AdminUserControllerUtils adminUserControllerUtils;

    public AdminBikeServiceController(BikeRepairService bikeRepairServiceService, AdminUserService userService, AdminUserControllerUtils adminUserControllerUtils) {
        this.bikeRepairServiceService = bikeRepairServiceService;
        this.adminUserControllerUtils = adminUserControllerUtils;
    }

    @GetMapping("/pending")
    public ResponseEntity<List<BikeRepairResponseDTO>> getAllPendingServices(@RequestHeader("token") String token) {
        adminUserControllerUtils.validateUser(token);
        List<BikeRepair> bikeRepairList = bikeRepairServiceService.getAllPendingServices();
        List<BikeRepairResponseDTO> bikeRepairResponseDTOList = new ArrayList<>();
        for (BikeRepair bikeRepair : bikeRepairList) {
            bikeRepairResponseDTOList.add(BikeRepairControllerUtils.mapToBikeRepairResponseDTO(bikeRepair));
        }
        return ResponseEntity.ok(bikeRepairResponseDTOList);
    }

    @GetMapping("/completed")
    public ResponseEntity<List<BikeRepairResponseDTO>> getAllCompletedServices(@RequestHeader("token") String token) {
        adminUserControllerUtils.validateUser(token);
        List<BikeRepair> bikeRepairList = bikeRepairServiceService.getAllCompletedServices();
        List<BikeRepairResponseDTO> bikeRepairResponseDTOList = new ArrayList<>();
        for (BikeRepair bikeRepair : bikeRepairList) {
            bikeRepairResponseDTOList.add(BikeRepairControllerUtils.mapToBikeRepairResponseDTO(bikeRepair));
        }
        return ResponseEntity.ok(bikeRepairResponseDTOList);
    }

    @PostMapping("/update")
    public ResponseEntity<BikeRepairResponseDTO> updateService(@RequestBody MechanicAssignDTO updateDTO, @RequestHeader("token") String token) {
        adminUserControllerUtils.validateUser(token);
        BikeRepair bikeRepair = bikeRepairServiceService.getServiceById(updateDTO.getId());
        BikeRepairControllerUtils.createBikeRepair(bikeRepair,updateDTO);
        BikeRepair updatedBikeRepair = bikeRepairServiceService.updateService(bikeRepair);
        return ResponseEntity.ok(BikeRepairControllerUtils.mapToBikeRepairResponseDTO(updatedBikeRepair));
    }

    @GetMapping("/mechanics")
    public ResponseEntity<List<Mechanic>> getMechanics(@RequestHeader("token") String token) {
        adminUserControllerUtils.validateUser(token);
        return ResponseEntity.ok(bikeRepairServiceService.getMechanics());
    }

    @PostMapping("/mechanics")
    public ResponseEntity<String> addMechanic(@RequestBody String mechanic, @RequestHeader("token") String token) {
        adminUserControllerUtils.validateUser(token);
        return ResponseEntity.ok(bikeRepairServiceService.addMechanic(mechanic));
    }
}
