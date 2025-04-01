package com.admin.sbsbackend.models;


import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Mechanic extends BaseModel {
    private String name;
    private String createdBy;
    private String updatedBy;
}
