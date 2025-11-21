package com.ms.patient.dto;

import com.ms.patient.enums.PatientSituation;

import java.util.Date;
import java.util.List;

/**
 * DTO (Data Transfer Object) utilizado para representar um Paciente nos retornos
 * de requisições GET, POST e PUT da API.
 *
 * <p>Inclui o identificador principal (ID), dados herdados de Pessoa, o endereço
 * mapeado como {@link AddressResponseDTO}, e todos os campos específicos do Paciente.</p>
 *
 * @param id O identificador único principal (ID da Pessoa/Paciente) no banco de dados.
 * @param name O nome completo do paciente.
 * @param birthDate A data de nascimento do paciente.
 * @param cpf O CPF do paciente.
 * @param gender O gênero da pessoa.
 * @param email O endereço de e-mail.
 * @param phone O número de telefone.
 * @param address O objeto DTO contendo a resposta completa do endereço.
 * @param healthPlan O plano de saúde do paciente.
 * @param description Uma descrição geral da condição do paciente.
 * @param symptoms A lista de sintomas registrados.
 * @param allergies A lista de alergias conhecidas.
 * @param patientSituation A situação clínica atual do paciente (enum {@link PatientSituation}).
 */
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