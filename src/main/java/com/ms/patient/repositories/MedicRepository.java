package com.ms.patient.repositories;

import com.ms.patient.models.Medic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicRepository extends JpaRepository<Medic, Long> {

    Boolean existsByCpf(String cpf);
    Boolean existsByCrm(String crm);
}
