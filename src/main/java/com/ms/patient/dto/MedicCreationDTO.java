package com.ms.patient.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import org.hibernate.validator.constraints.br.CPF;

import java.util.Date;

/**
 * DTO (Data Transfer Object) utilizado para a criação de um novo registro de Médico.
 *
 * <p>Este DTO combina dados herdados de Pessoa (Person); um objeto de endereço aninhado;
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
public class MedicCreationDTO {
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

        // --- CAMPOS ESPECÍFICOS DE MEDIC ---
        private String crm;
        private String medicalSpeciality;
        
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
        public String getCrm() {
                return crm;
        }
        public void setCrm(String crm) {
                this.crm = crm;
        }
        public String getMedicalSpeciality() {
                return medicalSpeciality;
        }
        public void setMedicalSpeciality(String medicalSpeciality) {
                this.medicalSpeciality = medicalSpeciality;
        }

        
}