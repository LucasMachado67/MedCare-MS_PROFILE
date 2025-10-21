package com.ms.patient.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.br.CPF;

import java.util.Date;
import java.util.List;

public record PatientCreationDTO(
        // Campos da Pessoa (Herança)
        @NotBlank String name,
        @NotNull Date birthDate,
        @NotBlank @CPF String cpf, // Adicionando validação de CPF
        @NotBlank String gender,
        @NotBlank @Email String email,
        @NotBlank String phone,

        // Objeto aninhado para Endereço
        @Valid AddressDTO address,

        // Campos Específicos do Paciente
        @NotBlank String healthPlan,
        String description, // Geralmente opcional
        List<String> symptoms, // Para mapear coleções
        List<String> allergies
) {}
