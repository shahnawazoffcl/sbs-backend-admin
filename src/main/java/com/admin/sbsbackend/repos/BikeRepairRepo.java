package com.admin.sbsbackend.repos;

import com.admin.sbsbackend.models.BikeRepair;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface BikeRepairRepo extends JpaRepository<BikeRepair, UUID>{
    List<BikeRepair> findBikeRepairsByServiceStatusNotIn(List<String> completed);
    List<BikeRepair> findBikeRepairsByServiceStatusIn(List<String> completed);
}
