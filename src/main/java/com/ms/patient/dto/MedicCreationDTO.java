package com.ms.patient.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.br.CPF;

import java.util.Date;

/**
 * DTO (Data Transfer Object) utilizado para a criação de um novo registro de Médico.
 *
 * <p>Este DTO combina dados herdados de Pessoa (Person), um objeto de endereço aninhado,
 * e campos específicos da entidade Médico, aplicando validações rigorosas.</p>
 *
 * @param name O nome completo do médico. Deve ser preenchido ({@code @NotBlank}).
 * @param birthDate A data de nascimento do médico. Deve ser preenchida ({@code @NotNull}).
 * @param cpf O CPF do médico. Deve ser preenchido e válido ({@code @NotBlank}, {@code @CPF}).
 * @param gender O gênero da pessoa. Deve ser preenchido ({@code @NotBlank}).
 * @param email O endereço de e-mail do médico. Deve ser preenchido e ter formato válido ({@code @NotBlank}, {@code @Email}).
 * @param phone O número de telefone do médico. Deve ser preenchido ({@code @NotBlank}).
 * @param address O objeto DTO contendo os dados do endereço do médico ({@code @Valid} para validação aninhada).
 * @param crm O número de registro do Conselho Regional de Medicina (CRM). Deve ser preenchido ({@code @NotBlank}).
 * @param medicalSpeciality A especialidade médica principal do profissional. Deve ser preenchida ({@code @NotBlank}).
 */
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