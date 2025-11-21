package com.ms.patient.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

/**
 * Entidade JPA que representa um Médico no sistema.
 *
 * <p>Herda todos os campos de identificação e contato da entidade {@link Person}.
 * Utiliza o mapeamento de herança de tabela única (Table-Per-Class/Joined) onde
 * os campos específicos do Médico são armazenados na tabela {@code medics},
 * referenciando a chave primária da tabela {@code persons} através da coluna {@code person_id}.</p>
 */
@Entity
@Table(name = "medics")
@PrimaryKeyJoinColumn(name = "person_id")
public class Medic extends Person{

    /**
     * O número de registro do Conselho Regional de Medicina (CRM).
     * <p>Restrição: Não nulo (NOT NULL).</p>
     */
    @NotNull
    private String crm;
    
    /**
     * A especialidade médica principal do profissional.
     * <p>Restrição: Não nulo (NOT NULL).</p>
     */
    @NotNull
    private String medicalSpeciality;

    /**
     * Construtor padrão exigido pelo JPA.
     */
    public Medic(){
        super();
    }

    /**
     * Construtor completo para criação de um Médico.
     *
     * @param name O nome completo (herdado de Person).
     * @param birthDate A data de nascimento (herdado de Person).
     * @param cpf O CPF (herdado de Person).
     * @param gender O gênero (herdado de Person).
     * @param email O e-mail (herdado de Person).
     * @param phone O telefone (herdado de Person).
     * @param address O objeto de endereço associado (herdado de Person).
     * @param crm O número de registro no CRM.
     * @param medicalSpeciality A especialidade médica.
     */
    public Medic(String name, Date birthDate, String cpf, String gender, String email, String phone,
                 Address address, String crm, String medicalSpeciality) {
        super(name, birthDate,cpf,gender,email,phone,address);
        setCrm(crm);
        setMedicalSpeciality(medicalSpeciality);
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
