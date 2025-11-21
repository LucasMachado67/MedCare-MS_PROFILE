package com.ms.patient.dto;

import java.util.Date;

/**
 * DTO (Data Transfer Object) utilizado para representar um Médico nos retornos
 * de requisições GET, POST e PUT da API.
 *
 * <p>Inclui o identificador principal (ID), dados herdados de Pessoa, o endereço
 * mapeado como {@link AddressResponseDTO}, e os campos específicos do Médico.</p>
 *
 * @param id O identificador único principal (ID da Pessoa/Médico) no banco de dados.
 * @param name O nome completo do médico.
 * @param birthDate A data de nascimento.
 * @param cpf O CPF do médico.
 * @param gender O gênero da pessoa.
 * @param email O endereço de e-mail.
 * @param phone O número de telefone.
 * @param address O objeto DTO contendo a resposta completa do endereço.
 * @param crm O número de registro do Conselho Regional de Medicina (CRM).
 * @param medicalSpeciality A especialidade médica principal.
 */
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