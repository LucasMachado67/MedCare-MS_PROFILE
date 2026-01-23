package com.ms.patient.dto;

import java.util.Date;

/**
 * DTO (Data Transfer Object) utilizado para representar um Médico nos retornos
 * de requisições GET; POST e PUT da API.
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
public class MedicResponseDTO{
        // --- ID PRINCIPAL (Herdado de Person) ---
        private Long id;

        // --- CAMPOS HERDADOS DE PERSON ---
        private String name;
        private Date birthDate;
        private String cpf;
        private String gender;
        private String email;
        private String phone;

        // --- ENDEREÇO ANINHADO ---
        private AddressResponseDTO address; // Usando o DTO de resposta do Address

        // --- CAMPOS ESPECÍFICOS DE MEDIC ---
        private String crm;
        private String medicalSpeciality;
        public Long getId() {
                return id;
        }
        public void setId(Long id) {
                this.id = id;
        }
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
        public AddressResponseDTO getAddress() {
                return address;
        }
        public void setAddress(AddressResponseDTO address) {
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