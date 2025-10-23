package com.ms.patient.service;

import com.ms.patient.dto.MedicCreationDTO;
import com.ms.patient.dto.MedicResponseDTO;
import com.ms.patient.mappers.MedicMapper;
import com.ms.patient.models.Medic;
import com.ms.patient.producers.UserCreationProducer;
import com.ms.patient.repositories.MedicRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class MedicService {
    
    private final MedicRepository repository;
    private final MedicMapper mapper;
    private final UserCreationProducer medicProducer;


    public MedicService(MedicRepository repository, MedicMapper mapper, UserCreationProducer medicProducer) {
        this.repository = repository;
        this.mapper = mapper;
        this.medicProducer = medicProducer;
    }

    public MedicResponseDTO createMedic(MedicCreationDTO dto){

        // 1. VALIDAÇÃO DE REGRA DE NEGÓCIO
        // Garantir que o CPF e o CRM são únicos antes de salvar.
        // No mundo real, esta verificação deve ser mais robusta.
        //If (repository.existsByCrm(dto.crm())) {
        //    //Criar uma exceção específica depois
        //    throw new RuntimeException("CRM já cadastrado.");
        //}
        //if (repository.existsByCpf(dto.cpf())) {
        //    throw new CpfAlreadyExistsException("CPF já cadastrado.");
        //}

        // 2. CONVERSÃO DTO ≥ ENTIDADE
        // O Mapper cuida da criação de Person, Address e Medic.
        Medic medic = mapper.toMedic(dto);

        // 3. PERSISTÊNCIA
        Medic savedMedic = repository.save(medic);

        // ---------------------------------------------
        // ENVIO DO EVENTO ASSÍNCRONO
        // ---------------------------------------------

        // 4. Criando o objeto de evento
        medicProducer.publishUserCreationToMedicEvent(savedMedic);
        
        return mapper.toMedicResponseDTO(savedMedic);
    }

    public List<MedicResponseDTO> findAll(){
        List<Medic> medics = repository.findAll();
        return mapper.toDtoResponse(medics);
    }

    public Medic findById(long id){
        return repository.findById(id).orElseThrow(() -> new NoSuchElementException("NOT FOUND"));
    }

}
