package com.admin.sbsbackend.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Mechanic extends BaseModel {
    private String name;
    @JsonIgnore
    private String createdBy;
    @JsonIgnore
    private String updatedBy;
}
