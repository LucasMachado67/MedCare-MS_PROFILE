package com.ms.patient.dto;

import java.util.Date;

/**
 * DTO (Data Transfer Object) utilizado para representar uma Pessoa nos retornos
 * de requisições GET, POST e PUT da API.
 *
 * <p>Inclui o identificador único (ID) gerado pelo sistema e todos os dados
 * básicos da pessoa, além do endereço aninhado.</p>
 */
public class PersonResponseDTO {

    private Long id; 
    private String name;
    private Date birthDate;
    private String cpf;
    private String gender;
    private String email;
    private String phone;
    
    private AddressDTO address;

    public PersonResponseDTO(){}
    public PersonResponseDTO(Long id, String name, Date birthDate, String cpf, String gender, String email,
            String phone, AddressDTO address) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
        this.cpf = cpf;
        this.gender = gender;
        this.email = email;
        this.phone = phone;
        this.address = address;
    }

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

    public AddressDTO getAddress() {
        return address;
    }

    public void setAddress(AddressDTO address) {
        this.address = address;
    } 

    
}
