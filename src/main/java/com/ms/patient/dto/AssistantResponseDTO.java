package com.ms.patient.dto;

import java.util.Date;

public class AssistantResponseDTO extends PersonResponseDTO{


    private String registrationNumber;
    private Boolean active;

    public String getRegistrationNumber() {
        return this.registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public boolean getActive() {
        return this.active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
