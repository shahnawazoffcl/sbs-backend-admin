package com.admin.sbsbackend.services.impl;

import com.admin.sbsbackend.exceptions.BikeServiceNotFoundException;
import com.admin.sbsbackend.models.BikeRepair;
import com.admin.sbsbackend.models.Mechanic;
import com.admin.sbsbackend.repos.BikeRepairRepo;
import com.admin.sbsbackend.repos.MechanicsRepo;
import com.admin.sbsbackend.services.BikeRepairService;
import com.twilio.rest.api.v2010.account.Message;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service
public class BikeRepairServiceImpl implements BikeRepairService {

    private final BikeRepairRepo serviceRepo;
    private final MechanicsRepo mechRepo;

    public BikeRepairServiceImpl(BikeRepairRepo serviceRepo, MechanicsRepo mechRepo) {
        this.serviceRepo = serviceRepo;
        this.mechRepo = mechRepo;
    }


    @Override
    public List<BikeRepair> getAllPendingServices() {
        return this.serviceRepo.findBikeRepairsByServiceStatusNotIn(List.of("COMPLETED"));
    }

    @Override
    public BikeRepair getServiceById(String serviceId) {
        Optional<BikeRepair> bikeRepairOptional = this.serviceRepo.findById(UUID.fromString(serviceId));
        if (bikeRepairOptional.isPresent()) {
            return bikeRepairOptional.get();
        } else {
            throw new BikeServiceNotFoundException("Service not found");
        }
    }

    @Override
    public BikeRepair updateService(BikeRepair service) {
        service.setUpdatedBy(null);
        return this.serviceRepo.save(service);
    }

    @Override
    public List<BikeRepair> getAllCompletedServices() {
        List<BikeRepair> bikeRepairList = this.serviceRepo.findBikeRepairsByServiceStatusIn(List.of("COMPLETED"));
        return bikeRepairList;
    }

    @Override
    public List<Mechanic> getMechanics() {
        return this.mechRepo.findAll();
    }

    @Override
    public String addMechanic(String mechanic) {
        Mechanic newMechanic = new Mechanic();
        newMechanic.setName(mechanic);
        this.mechRepo.save(newMechanic);

        return "added";
    }
}
