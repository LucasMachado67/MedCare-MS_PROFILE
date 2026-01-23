package com.ms.patient.repositories;

import com.ms.patient.models.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interface de repositório responsável por operações de acesso a dados (CRUD)
 * para a entidade {@link Patient}.
 *
 * <p>Estende {@link org.springframework.data.jpa.repository.JpaRepository},
 * fornecendo implementações padrão para métodos de persistência.</p>
 *
 * @author Lucas Edson Machado
 * @since 2025-11-17
 */
@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
}
