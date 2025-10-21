package com.ms.patient.dto;

import com.ms.patient.enums.PatientSituation;

import java.util.Date;
import java.util.List;

public record PatientResponseDTO(
        // ID Principal da Pessoa (Herdado)
        Long id,

        // Campos da Pessoa
        String name,
        Date birthDate,
        String cpf,
        String gender,
        String email,
        String phone,

        // Objeto aninhado para Endereço
        AddressResponseDTO address,

        // Campos Específicos do Paciente
        String healthPlan,
        String description,
        List<String> symptoms,
        List<String> allergies,
        PatientSituation patientSituation // Retorna o status atual
) {}
