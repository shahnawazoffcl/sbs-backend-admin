package com.admin.sbsbackend.models;


import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@Setter
@Getter
@Entity
public class Expense extends BaseModel{
    @ManyToOne
    private BikeRepair bikeRepair;
    private String expenseType;
    private String expenseDescription;
    private double expenseAmount;
    private Date createdAt;
    private LocalDate createdDate;

}
