package com.ms.patient.mappers;

import com.ms.patient.dto.PatientCreationDTO;
import com.ms.patient.dto.PatientResponseDTO;
import com.ms.patient.models.Patient;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan
@Mapper(componentModel = "spring", uses = { AddressMapper.class })
public interface PatientMapper {
    
    @Mapping(source = "address", target = "address")
    Patient toPatient(PatientCreationDTO dto);

    @Mapping(source = "address", target = "address") // Mapeia Address Entity -> AddressResponseDTO
    PatientResponseDTO toPatientResponseDTO(Patient patient);

}
