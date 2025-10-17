package com.ms.patient.models;

import java.util.ArrayList;

import com.ms.patient.enums.PatientSituation;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;

@Entity
@Table(name = "patients")
public class Patient{


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int patient_id;
    private int person_id;
    private String healthPlan;
    @Lob
    private String description;
    private ArrayList<String> symptoms;
    private ArrayList<String> allergies;
    private PatientSituation patientSituation;

    public Patient(int person_id, String description, String healthPlan, PatientSituation patientSituation) {
        setPersonId(person_id);
        this.symptoms = new ArrayList<>();
        this.allergies = new ArrayList<>();
        setHealthPlan(healthPlan);
        setDescription(description);
        setPatientSituation(patientSituation);
    }

    public int getPersonId(){
        return this.person_id;
    }
    public void setPersonId(int person_id){
        this.person_id = person_id;
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
