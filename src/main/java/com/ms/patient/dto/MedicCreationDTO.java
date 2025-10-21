package com.ms.patient.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.br.CPF;

import java.util.Date;

public record MedicCreationDTO (
        // --- CAMPOS HERDADOS DE PERSON ---
        @NotBlank String name,
        @NotNull Date birthDate,
        @NotBlank @CPF String cpf,
        @NotBlank String gender,
        @NotBlank @Email String email,
        @NotBlank String phone,

        // --- ENDEREÇO ANINHADO ---
        @Valid AddressDTO address, // Usando o DTO de requisição do Address

        // --- CAMPOS ESPECÍFICOS DE MEDIC ---
        @NotBlank String crm,
        @NotBlank String medicalSpeciality
){}
