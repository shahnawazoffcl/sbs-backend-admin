package com.admin.sbsbackend.models;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Setter
@Getter
@Entity
public class BikeRepair extends BaseModel implements Serializable {
    private String name;
    private String bikeModel;
    private String serviceType;
    private String contactNo;
    private String address;
    private String preferredTime;
    private String serviceStatus;
    @OneToOne
    private Mechanic mechanic;
    private Date createdDate;
    private String createdBy;
    @OneToOne
    private User updatedBy;

    @PrePersist
    public void prePersist() {
        // Do not set 'column2' value explicitly
        if (serviceStatus == null) {
            serviceStatus = "PENDING";  // Only set manually if not set
        }
    }


    @Override
    public String toString() {
        return "BikeService{" +
                "name='" + name + '\'' +
                ", bikeModel='" + bikeModel + '\'' +
                ", serviceType=" + serviceType +
                ", contactNo='" + contactNo + '\'' +
                ", address='" + address + '\'' +
                ", preferredTime='" + preferredTime + '\'' +
                '}';
    }
}
