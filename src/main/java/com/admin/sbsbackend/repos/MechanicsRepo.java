package com.admin.sbsbackend.repos;

import com.admin.sbsbackend.models.Mechanic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MechanicsRepo extends JpaRepository<Mechanic, UUID> {
    Mechanic findMechanicById(UUID id);
}
