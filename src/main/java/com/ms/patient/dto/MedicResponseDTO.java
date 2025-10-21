package com.ms.patient.dto;

import java.util.Date;

public record MedicResponseDTO(
        // --- ID PRINCIPAL (Herdado de Person) ---
        Long id,

        // --- CAMPOS HERDADOS DE PERSON ---
        String name,
        Date birthDate,
        String cpf,
        String gender,
        String email,
        String phone,

        // --- ENDEREÇO ANINHADO ---
        AddressResponseDTO address, // Usando o DTO de resposta do Address

        // --- CAMPOS ESPECÍFICOS DE MEDIC ---
        String crm,
        String medicalSpeciality

        // NOTA: O ID do Medic (medic_id) não é essencial aqui se você usa o ID da Person como chave principal.
) {}
