package com.admin.sbsbackend.services;

import com.admin.sbsbackend.models.BikeRepair;
import com.admin.sbsbackend.models.Mechanic;
import org.springframework.scheduling.annotation.Async;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface BikeRepairService {

    List<BikeRepair> getAllPendingServices();

    BikeRepair getServiceById(String serviceId);

    BikeRepair updateService(BikeRepair service);

    List<BikeRepair> getAllCompletedServices();

    List<Mechanic> getMechanics();

    String addMechanic(String mechanic);
}
