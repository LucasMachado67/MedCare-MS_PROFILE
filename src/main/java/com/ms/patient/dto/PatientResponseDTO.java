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
public class PatientResponseDTO {

        //Id da Pessoa
        private int id;
        //Campos da classe person
        private String name;
        private Date birthDate;
        private String cpf;
        private String gender;
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
        
        public int getId() {
                return id;
        }
        public void setId(int id) {
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