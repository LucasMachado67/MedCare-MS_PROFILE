package com.ms.patient.dto;

import java.util.Date;

import org.hibernate.validator.constraints.br.CPF;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;

public class AssistantCreationDTO {

    // --- CAMPOS HERDADOS DE PERSON ---
    private String name;
    private Date birthDate;
    @CPF
    private String cpf;
    private String gender;
    @Email
    private String email;
    private String phone;

    // --- ENDEREÇO ANINHADO ---
    @Valid
    private AddressDTO address; // Usando o DTO de requisição do Address

    private String registrationNumber;
    private Boolean active;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public AddressDTO getAddress() {
        return address;
    }

    public void setAddress(AddressDTO address) {
        this.address = address;
    }

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
