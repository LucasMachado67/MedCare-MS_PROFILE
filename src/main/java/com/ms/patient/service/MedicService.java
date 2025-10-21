package com.ms.patient.service;

import com.ms.patient.dto.MedicCreationDTO;
import com.ms.patient.events.MedicRegisteredEvent;
import com.ms.patient.dto.MedicResponseDTO;
import com.ms.patient.mappers.MedicMapper;
import com.ms.patient.models.Medic;
import com.ms.patient.repositories.MedicRepository;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicService {

    private final MedicRepository repository;
    private final MedicMapper mapper;
    private final StreamBridge streamBridge; // Apenas o StreamBridge é necessário

    private static final String CHANNEL_NAME = "medicRegistrationSupplier"; // Nome consistente

    // Injeção via construtor (melhor prática)
    public MedicService(MedicRepository repository, MedicMapper mapper, StreamBridge streamBridge) {
        this.repository = repository;
        this.mapper = mapper;
        this.streamBridge = streamBridge;
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
        MedicRegisteredEvent event = new MedicRegisteredEvent(
                savedMedic.getId(),
                savedMedic.getEmail(),
                "MEDIC"
        );
        // --- ENVIANDO O EVENTO COM STREAM BRIDGE ---
        boolean sent = streamBridge.send(CHANNEL_NAME, event);

        if (!sent) {
            System.err.println("CRITICAL: Falha ao enviar evento de registry de médico.");
        }
        // 6. CONVERSÃO ENTIDADE -> RESPOSTA
        return mapper.toMedicResponseDTO(savedMedic);
    }

    public List<MedicResponseDTO> findAll(){
        List<Medic> medics = repository.findAll();
        return mapper.toDtoResponse(medics);
    }

    public MedicResponseDTO findById(long id){
        Medic medic = repository.findById(id).orElseThrow();
        return mapper.toMedicResponseDTO(medic);
    }

}
