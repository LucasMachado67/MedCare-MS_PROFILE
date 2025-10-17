package com.ms.patient.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ms.patient.models.Address;


@Repository
public interface AddressRepository extends JpaRepository<Address, Long>{
    
}
