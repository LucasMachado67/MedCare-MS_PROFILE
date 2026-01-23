package com.ms.patient.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ms.patient.models.Assistant;

@Repository
public interface AssistantRepository extends JpaRepository<Assistant, Long>{

}
