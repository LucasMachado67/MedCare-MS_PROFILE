package com.ms.patient.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;

import com.ms.patient.dto.AssistantCreationDTO;
import com.ms.patient.dto.AssistantResponseDTO;
import com.ms.patient.exceptions.CpfAlreadyExistsException;
import com.ms.patient.mappers.AssistantMapper;
import com.ms.patient.models.Assistant;
import com.ms.patient.models.Medic;
import com.ms.patient.producers.UserCreationProducer;
import com.ms.patient.repositories.AssistantRepository;

@Service
public class AssistantService {

    private final AssistantRepository repository;
    private final AssistantMapper mapper;
    private final UserCreationProducer assistantProducer;

    /**
     * Construtor para injeção de dependências dos componentes de persistência,
     * mapeamento e produção de eventos.
     *
     * @param repository O repositório para acesso a dados de {@link Medic}.
     * @param mapper O mapeador para conversão entre DTOs e entidades.
     * @param assistantProducer O produtor de eventos para criação de usuários.
     */
    public AssistantService(AssistantRepository repository, AssistantMapper mapper, UserCreationProducer assistantProducer) {
        this.repository = repository;
        this.mapper = mapper;
        this.assistantProducer = assistantProducer;
    }

    /**
     * Cria e persiste um novo assistente no sistema, aplicando as regras de negócio.
     *
     *
     * @param dto O DTO de criação contendo os dados do novo assistente.
     * @return O {@link AssistantResponseDTO} do assistente recém-criado.
     * @throws RuntimeException se o CRM já estiver cadastrados.
     * @throws CpfAlreadyExistsException se o Cpf já estiver cadastrados.
     */
    public AssistantResponseDTO createAssistant(AssistantCreationDTO dto){

        // 1. VALIDAÇÃO DE REGRA DE NEGÓCIO
        //If (repository.existsByCrm(dto.crm())) {
        //    //Criar uma exceção específica depois
        //    throw new RuntimeException("CRM já cadastrado.");
        //}
        //if (repository.existsByCpf(dto.cpf())) {
        //    throw new CpfAlreadyExistsException("CPF já cadastrado.");
        //}

        // 2. CONVERSÃO DTO ≥ ENTIDADE
        // O Mapper cuida da criação de Person, Address e Assistant.
        Assistant assistant = mapper.toAssistant(dto);

        // 3. PERSISTÊNCIA
        Assistant savedAssistant = repository.save(assistant);

        // ---------------------------------------------
        // ENVIO DO EVENTO ASSÍNCRONO
        // ---------------------------------------------

        // 4. Criando o objeto de evento
        assistantProducer.publishUserCreationToAssistantEvent(savedAssistant);
        
        return mapper.toAssistantResponseDTO(savedAssistant);
    }

    /**
     * Retorna uma lista de todos os assistentes cadastrados no sistema.
     *
     * @return Uma {@link List} de {@link AssistantResponseDTO}s. Pode ser uma lista vazia,
     * mas nunca {@code null}.
     */
    public List<AssistantResponseDTO> findAll(){
        List<Assistant> assistants = repository.findAll();
        return mapper.toDtoResponse(assistants);
    }

    /**
     * Busca um assistente pelo seu identificador único.
     *
     * @param id O ID do assistente a ser procurado.
     * @return A entidade {@link Medic} encontrada.
     * @throws NoSuchElementException Se nenhum assistente for encontrado com o ID fornecido.
     */
    public Assistant findById(long id){
        return repository.findById(id).orElseThrow(() -> new NoSuchElementException("NOT FOUND"));
    }
}
