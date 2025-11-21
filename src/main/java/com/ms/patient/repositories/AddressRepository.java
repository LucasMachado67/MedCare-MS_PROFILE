package com.ms.patient.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ms.patient.models.Address;

/**
 * Interface de repositório responsável por operações de acesso a dados (CRUD)
 * para a entidade {@link Address}.
 *
 * <p>Estende {@link org.springframework.data.jpa.repository.JpaRepository},
 * fornecendo implementações padrão para métodos de persistência.</p>
 *
 * @author Lucas Edson Machado
 * @since 2025-11-17
 */
@Repository
public interface AddressRepository extends JpaRepository<Address, Long>{
    
}
