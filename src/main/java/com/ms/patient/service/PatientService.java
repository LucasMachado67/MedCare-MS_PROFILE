package com.ms.patient.service;

import org.springframework.stereotype.Service;

import com.ms.patient.dto.PatientCreationDTO;
import com.ms.patient.dto.PatientResponseDTO;
import com.ms.patient.mappers.PatientMapper;
import com.ms.patient.producers.UserCreationProducer;
import com.ms.patient.repositories.PatientRepository;

@Service
public class PatientService {


    private final PatientRepository repository;
    private final PatientMapper mapper;
    private final UserCreationProducer userProducer;


    public PatientService(PatientRepository repository, PatientMapper mapper, UserCreationProducer userProducer) {
        this.repository = repository;
        this.mapper = mapper;
        this.userProducer = userProducer;
    }

    public PatientResponseDTO createPatient(PatientCreationDTO dto){

        // 1. VALIDAÇÃO DE REGRA DE NEGÓCIO
        // Garantir que o CPF é único antes de salvar.
        // No mundo real, esta verificação deve ser mais robusta.
        //If (repository.existsByCpf(dto.cpf())) {
        //    //Criar uma exceção específica depois
        //    throw new CpfAlreadyExistsException("CPF já cadastrado.");
        //}
        // 2. CONVERSÃO DTO ≥ ENTIDADE
        // O Mapper cuida da criação de Person, Address e Patient.
        var patient = mapper.toPatient(dto);
        // 3. PERSISTÊNCIA
        var savedPatient = repository.save(patient);
        // ---------------------------------------------
        // ENVIO DO EVENTO ASSÍNCRONO
        // ---------------------------------------------
        // 4. Criando o objeto de evento
        userProducer.publishUserCreationToPatientEvent(savedPatient);
        return mapper.toPatientResponseDTO(savedPatient);
    }
}
