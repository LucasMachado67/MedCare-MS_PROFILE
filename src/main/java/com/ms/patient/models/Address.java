package com.ms.patient.models;

import com.ms.patient.enums.Habitation;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

/**
 * Entidade JPA que representa um Endereço no sistema.
 *
 * <p>Mapeada para a tabela {@code address} no banco de dados. Armazena detalhes
 * geográficos e o tipo de habitação associado a uma Pessoa.</p>
 */
@Entity
@Table(name="address")
public class Address {

    /**
     * Chave primária (Primary Key) da entidade.
     * <p>Auto-gerada pelo banco de dados usando estratégia de identidade.</p>
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    /**
     * O bairro do endereço.
     * <p>Restrição: Não nulo (NOT NULL).</p>
     */
    @NotNull
    private String neighborhood;
    
    /**
     * O logradouro (nome da rua ou avenida).
     * <p>Restrição: Não nulo (NOT NULL).</p>
     */
    @NotNull
    private String street;
    
    /**
     * O número da residência ou estabelecimento.
     * <p>Restrição: Não nulo (NOT NULL).</p>
     */
    @NotNull
    private int number;
    
    /**
     * Informações adicionais do endereço (ex: "Apto 101", "Bloco B").
     * <p>Restrição: Não nulo (NOT NULL).</p>
     */
    @NotNull
    private String complement;
    
    /**
     * O nome da cidade.
     * <p>Restrição: Não nulo (NOT NULL).</p>
     */
    @NotNull
    private String city;
    
    /**
     * O Código de Endereçamento Postal (CEP).
     * <p>Restrição: Não nulo (NOT NULL).</p>
     */
    @NotNull
    private String zipCode;
    
    /**
     * A sigla do estado (UF).
     * <p>Restrição: Não nulo (NOT NULL).</p>
     */
    @NotNull
    private String state;
    
    /**
     * O tipo de habitação (ex: APARTMENT, HOUSE).
     * <p>Persistido como String no banco de dados.</p>
     * <p>Restrição: Não nulo (NOT NULL).</p>
     * @see com.ms.patient.enums.Habitation
     */
    @NotNull
    @Enumerated(EnumType.STRING)
    private Habitation habitation;

    public Address(){}
    
    /**
     * Construtor para criação da entidade Address.
     *
     * @param neighborhood O bairro.
     * @param street O logradouro.
     * @param number O número.
     * @param city A cidade.
     * @param zipCode O CEP.
     * @param habitation O tipo de habitação (da enumeração {@link Habitation}).
     * @param state O estado (UF).
     * @param complement O complemento.
     */
    public Address(String neighborhood, String street, int number, String city, String zipCode, Habitation habitation, String state, String complement){
        setNeighborhood(neighborhood);
        setStreet(street);
        setNumber(number);
        setCity(city);
        setZipCode(zipCode);
        setComplement(complement);
        setState(state);
        setHabitation(habitation);
    }

    
    public String getNeighborhood() {
        return neighborhood;
    }
    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }
    public String getStreet() {
        return street;
    }
    public void setStreet(String street) {
        this.street = street;
    }
    public int getNumber() {
        return number;
    }
    public void setNumber(int number) {
        this.number = number;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public String getZipCode() {
        return zipCode;
    }
    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }  
    public Habitation getHabitation() {
        return habitation;
    }
    public void setHabitation(Habitation habitation) {
        this.habitation = habitation;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getComplement() {
        return complement;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    

}
