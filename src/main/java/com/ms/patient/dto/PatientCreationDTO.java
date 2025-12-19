package com.ms.patient.dto;

import jakarta.validation.constraints.Email;
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
public class PatientCreationDTO{
        // Campos da Pessoa (Herança)
        private String name;
        private Date birthDate;
        @CPF
        private String cpf;
        private String gender;
        @Email
        private String email;
        private String phone;
        //Compos da classe address
        private AddressResponseDTO address;
        //campos da classe patient
        private String healthPlan;
        private String description;
        private List<String> symptoms;
        private List<String> allergies;
        private PatientSituation patientSituation;

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
        public String getHealthPlan() {
                return healthPlan;
        }
        public void setHealthPlan(String healthPlan) {
                this.healthPlan = healthPlan;
        }
        public String getDescription() {
                return description;
        }
        public void setDescription(String description) {
                this.description = description;
        }
        public List<String> getSymptoms() {
                return symptoms;
        }
        public void setSymptoms(List<String> symptoms) {
                this.symptoms = symptoms;
        }
        public List<String> getAllergies() {
                return allergies;
        }
        public void setAllergies(List<String> allergies) {
                this.allergies = allergies;
        }
        public PatientSituation getPatientSituation() {
                return patientSituation;
        }
        public void setPatientSituation(PatientSituation patientSituation) {
                this.patientSituation = patientSituation;
        }

        
}