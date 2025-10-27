package com.ms.patient.models;

import java.util.ArrayList;
import java.util.Date;

import com.ms.patient.enums.PatientSituation;

import jakarta.persistence.*;

@Entity
@Table(name = "patients")
@PrimaryKeyJoinColumn(name = "person_id")
public class Patient extends Person{

    private String healthPlan;
    @Lob
    private String description;
    private ArrayList<String> symptoms;
    private ArrayList<String> allergies;
    private PatientSituation patientSituation;

    public Patient(){
        super();
    }

    public Patient(String name, Date birthDate, String cpf, String gender, String email, String phone,
                   Address address, String description, String healthPlan, PatientSituation patientSituation) {
        super(name,birthDate,cpf,gender,email,phone,address);
        this.symptoms = new ArrayList<>();
        this.allergies = new ArrayList<>();
        setHealthPlan(healthPlan);
        setDescription(description);
        setPatientSituation(patientSituation);
    }

    public String getHealthPlan() {
        return healthPlan;
    }

    public void setHealthPlan(String healthPlan) {
        this.healthPlan = healthPlan;
    }

    public String getDescription() {
        return description;
    }


    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<String> getSymptoms() {
        return symptoms;
    }


    public void setSymptoms(ArrayList<String> symptoms) {
        this.symptoms = symptoms;
    }


    public ArrayList<String> getAllergies() {
        return allergies;
    }


    public void setAllergies(ArrayList<String> allergies) {
        this.allergies = allergies;
    }


    public PatientSituation getPatientSituation() {
        return patientSituation;
    }


    public void setPatientSituation(PatientSituation patientSituation) {
        this.patientSituation = patientSituation;
    }

    
    
}
