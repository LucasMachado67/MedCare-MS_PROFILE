package com.ms.patient.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ms.patient.dto.MedicCreationDTO;
import com.ms.patient.dto.MedicResponseDTO;
import com.ms.patient.exceptions.CpfAlreadyExistsException;
import com.ms.patient.mappers.MedicMapper;
import com.ms.patient.models.Medic;
import com.ms.patient.producers.UserCreationProducer;
import com.ms.patient.repositories.MedicRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

/**
 * Serviço responsável por orquestrar a lógica de negócio (CRUD e validações)
 * para a entidade {@link Medic} (Médico).
 *
 * <p>Esta classe interage com o {@link MedicRepository} para persistência
 * e utiliza o {@link UserCreationProducer} para comunicação assíncrona,
 * como a criação de contas de usuário associadas.</p>
 *
 * @author Lucas Edson Machado
 * @since 2025-11-17
 */
@Service
public class MedicService {
    
    
    private final MedicRepository repository;
    private final MedicMapper mapper;
    private final UserCreationProducer medicProducer;

    /**
     * Construtor para injeção de dependências dos componentes de persistência,
     * mapeamento e produção de eventos.
     *
     * @param repository O repositório para acesso a dados de {@link Medic}.
     * @param mapper O mapeador para conversão entre DTOs e entidades.
     * @param medicProducer O produtor de eventos para criação de usuários.
     */
    public MedicService(MedicRepository repository, MedicMapper mapper, UserCreationProducer medicProducer) {
        this.repository = repository;
        this.mapper = mapper;
        this.medicProducer = medicProducer;
    }

    /**
     * Cria e persiste um novo médico no sistema, aplicando as regras de negócio.
     *
     * <p>O processo envolve: 1. Validação de unicidade do CRM e CPF (se ativada);
     * 2. Conversão do DTO para a entidade {@link Medic}; 3. Persistência;
     * 4. Publicação de um evento de criação de usuário assíncrono.</p>
     *
     * @param dto O DTO de criação contendo os dados do novo médico.
     * @return O {@link MedicResponseDTO} do médico recém-criado.
     * @throws JsonProcessingException 
     * @throws RuntimeException se o CRM já estiver cadastrados.
     * @throws CpfAlreadyExistsException se o Cpf já estiver cadastrados.
     */
    public MedicResponseDTO createMedic(MedicCreationDTO dto) throws JsonProcessingException{

        // 1. VALIDAÇÃO DE REGRA DE NEGÓCIO
        // Garantir que o CPF e o CRM são únicos antes de salvar.
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

    /**
     * Retorna uma lista de todos os médicos cadastrados no sistema.
     *
     * @return Uma {@link List} de {@link MedicResponseDTO}s. Pode ser uma lista vazia,
     * mas nunca {@code null}.
     */
    public List<MedicResponseDTO> findAll(){
        List<Medic> medics = repository.findAll();
        return mapper.toDtoResponse(medics);
    }

    /**
     * Busca um médico pelo seu identificador único.
     *
     * @param id O ID do médico a ser procurado.
     * @return A entidade {@link Medic} encontrada.
     * @throws NoSuchElementException Se nenhum médico for encontrado com o ID fornecido.
     */
    public Medic findById(long id){
        return repository.findById(id).orElseThrow(() -> new NoSuchElementException("NOT FOUND"));
    }

}
