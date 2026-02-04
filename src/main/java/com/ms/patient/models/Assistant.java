package com.ms.patient.models;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "employees")
@PrimaryKeyJoinColumn(name = "person_id")
public class Assistant extends Person{

    private String registrationNumber;
    @NotNull
    private Boolean active;

    public Assistant(String name, Date birthDate, String cpf, String gender, String email, String phone, Address address,
            String registrationNumber, Boolean active) {
        super(name, birthDate, cpf, gender, email, phone, address);
        this.setRegistrationNumber(registrationNumber);
        this.setActive(active);
    }

    public Assistant() {
        super();
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }
    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }
    public Boolean getActive() {
        return active;
    }
    public void setActive(Boolean active) {
        this.active = active;
    }

    

    
}
