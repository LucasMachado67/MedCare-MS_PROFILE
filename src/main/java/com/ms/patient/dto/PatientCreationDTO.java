package com.ms.patient.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.br.CPF;

import com.ms.patient.enums.PatientSituation;

import java.util.Date;
import java.util.List;

/**
 * DTO (Data Transfer Object) utilizado para a criação de um novo registro de Paciente.
 *
 * <p>Este DTO combina dados herdados de Pessoa, um objeto de endereço aninhado,
 * e campos específicos da entidade Paciente, aplicando validações rigorosas.</p>
 *
 * @param name O nome completo do paciente. Deve ser preenchido ({@code @NotBlank}).
 * @param birthDate A data de nascimento do paciente. Deve ser preenchida ({@code @NotNull}).
 * @param cpf O CPF do paciente. Deve ser preenchido e válido ({@code @NotBlank}, {@code @CPF}).
 * @param gender O gênero da pessoa. Deve ser preenchido ({@code @NotBlank}).
 * @param email O endereço de e-mail do paciente. Deve ser preenchido e ter formato válido ({@code @NotBlank}, {@code @Email}).
 * @param phone O número de telefone do paciente. Deve ser preenchido ({@code @NotBlank}).
 * @param address O objeto DTO contendo os dados do endereço do paciente ({@code @Valid} para validação aninhada).
 * @param healthPlan O nome ou código do plano de saúde do paciente. Deve ser preenchido ({@code @NotBlank}).
 * @param description Uma descrição geral ou anotação sobre o paciente ou sua condição. Campo opcional.
 * @param symptoms Uma lista de sintomas reportados pelo paciente ou observados. Campo opcional.
 * @param allergies Uma lista de alergias conhecidas do paciente. Campo opcional.
 * @param patientSituation A situação clínica ou estado atual do paciente (enum {@link PatientSituation}). Campo opcional na criação.
 */
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
        List<String> allergies,
        PatientSituation patientSituation
) {}