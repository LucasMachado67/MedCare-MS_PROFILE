package com.ms.patient.models;

import java.util.ArrayList;
import java.util.Date;

import com.ms.patient.enums.PatientSituation;

import jakarta.persistence.*;

/**
 * Entidade JPA que representa um Paciente no sistema de gestão de saúde.
 *
 * <p>Herda todos os campos de identificação e contato da entidade {@link Person}.
 * Os campos específicos do Paciente são armazenados na tabela {@code patients},
 * referenciando a chave primária da tabela {@code persons} (Table-Per-Class/Joined).</p>
 */
@Entity
@Table(name = "patients")
@PrimaryKeyJoinColumn(name = "person_id")
public class Patient extends Person{

    /**
     * O nome ou código do plano de saúde do paciente.
     */
    private String healthPlan;
    
    /**
     * Uma descrição detalhada ou anotações clínicas sobre o paciente ou sua condição.
     * <p>Mapeado como um Large Object (LOB) no banco de dados, suportando textos longos.</p>
     */
    @Lob
    private String description;
    
    /**
     * Lista de sintomas reportados pelo paciente ou observados.
     * <p>Nota: Para persistir {@code ArrayList<String>} em JPA, é comum usar {@code @ElementCollection}
     * ou transformar em JSON/String antes de salvar, dependendo da configuração do banco/Hibernate.</p>
     */
    private ArrayList<String> symptoms;
    
    /**
     * Lista de alergias conhecidas do paciente.
     * <p>Nota: Para persistir {@code ArrayList<String>} em JPA, é comum usar {@code @ElementCollection}
     * ou transformar em JSON/String antes de salvar, dependendo da configuração do banco/Hibernate.</p>
     */
    private ArrayList<String> allergies;
    
    /**
     * A situação clínica ou estado atual de acompanhamento do paciente.
     * <p>O valor padrão (default) é {@link PatientSituation#NOT_DEFINED}.</p>
     * @see com.ms.patient.enums.PatientSituation
     */
    @Enumerated(EnumType.STRING) // Garante que o enum seja salvo como String
    private PatientSituation patientSituation = PatientSituation.NOT_DEFINED;

    /**
     * Construtor padrão exigido pelo JPA.
     */
    public Patient(){
        super();
    }

    /**
     * Construtor para criação de um Paciente.
     *
     * @param name O nome completo (herdado de Person).
     * @param birthDate A data de nascimento (herdado de Person).
     * @param cpf O CPF (herdado de Person).
     * @param gender O gênero (herdado de Person).
     * @param email O e-mail (herdado de Person).
     * @param phone O telefone (herdado de Person).
     * @param address O objeto de endereço associado (herdado de Person).
     * @param description A descrição clínica ou anotação.
     * @param healthPlan O plano de saúde.
     * @param patientSituation A situação inicial do paciente.
     */
    public Patient(String name, Date birthDate, String cpf, String gender, String email, String phone,
                    Address address, String description, String healthPlan, PatientSituation patientSituation) {
        super(name,birthDate,cpf,gender,email,phone,address);
        this.symptoms = new ArrayList<>();
        this.allergies = new ArrayList<>();
        setHealthPlan(healthPlan);
        setDescription(description);
        setPatientSituation(patientSituation);
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

    public ArrayList<String> getSymptoms() {
        return symptoms;
    }


    public void setSymptoms(ArrayList<String> symptoms) {
        this.symptoms = symptoms;
    }


    public ArrayList<String> getAllergies() {
        return allergies;
    }


    public void setAllergies(ArrayList<String> allergies) {
        this.allergies = allergies;
    }


    public PatientSituation getPatientSituation() {
        return patientSituation;
    }


    public void setPatientSituation(PatientSituation patientSituation) {
        this.patientSituation = patientSituation;
    }
}
