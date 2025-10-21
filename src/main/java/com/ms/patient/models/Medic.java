package com.ms.patient.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

@Entity
@Table(name = "medics")
@PrimaryKeyJoinColumn(name = "person_id")
public class Medic extends Person{

    @NotNull
    private String crm;
    @NotNull
    private String medicalSpeciality;

    public Medic(String name, Date birthDate, String cpf, String gender, String email, String phone,
                 Address address, String crm, String medicalSpeciality) {
        super(name, birthDate,cpf,gender,email,phone,address);
        setCrm(crm);
        setMedicalSpeciality(medicalSpeciality);
    }

    public String getCrm() {
        return crm;
    }

    public void setCrm(String crm) {
        this.crm = crm;
    }

    public String getMedicalSpeciality() {
        return medicalSpeciality;
    }

    public void setMedicalSpeciality(String medicalSpeciality) {
        this.medicalSpeciality = medicalSpeciality;
    }
}
