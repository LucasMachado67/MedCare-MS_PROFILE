package com.ms.patient.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ms.patient.models.Person;



@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
    
    Person findPersonByEmail(String email);

    /*
     * . A consulta para de rodar assim que encontra o primeiro registro e retorna apenas um
     */

    Boolean existsByCpf(String cpf);

    Boolean existsByEmail(String email);

    Boolean existsByPhone(String phone);

}
