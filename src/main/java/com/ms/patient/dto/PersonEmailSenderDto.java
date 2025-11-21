package com.ms.patient.dto;

/**
 * DTO (Data Transfer Object) simplificado, usado especificamente para fornecer
 * informações mínimas de uma pessoa (ID, Email, Nome) para serviços externos,
 * como um serviço de envio de e-mail.
 */
public class PersonEmailSenderDto {

    private long id;
    private String email;
    private String nome;
    
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    
}
