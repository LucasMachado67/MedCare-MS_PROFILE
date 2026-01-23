package com.ms.patient.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;

import com.ms.patient.dto.PatientCreationDTO;
import com.ms.patient.dto.PatientResponseDTO;
import com.ms.patient.enums.PatientSituation;
import com.ms.patient.exceptions.CpfAlreadyExistsException;
import com.ms.patient.exceptions.InvalidCpfException;
import com.ms.patient.mappers.PatientMapper;
import com.ms.patient.models.Patient;
import com.ms.patient.producers.UserCreationProducer;
import com.ms.patient.repositories.PatientRepository;

/**
 * Serviço responsável por gerenciar toda a lógica de negócio (CRUD e validações)
 * para a entidade {@link Patient}.
 *
 * <p>Esta classe interage com o {@link PatientRepository} para persistência
 * e utiliza o {@link UserCreationProducer} para comunicação assíncrona.</p>
 *
 * @author Lucas Edson machado
 * @since 2025-11-17
 */
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
     /**
     * Cria e persiste um novo paciente no sistema, aplicando as regras de negócio.
     *
     * <p>O processo envolve: 1. Conversão do DTO para a entidade {@link Patient};
     * 2. Definição da situação inicial do paciente; 3. Persistência no banco de dados;
     * 4. Publicação de um evento de criação de usuário para o serviço de autenticação
     * (uso do {@link UserCreationProducer}).
     *
     * @param dto O DTO de criação contendo os dados brutos do paciente.
     * @return O {@link PatientResponseDTO} do paciente recém-criado.
     * @throws CpfAlreadyExistsException se o CPF do DTO já estiver cadastrado (descomentar validação).
     * @throws InvalidCpfException se o CPF for inválido (descomentar validação).
     */
    public PatientResponseDTO createPatient(PatientCreationDTO dto){

        //Está comentado para ser mais fácil de testar várias requisições
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
        //Coloca NOT_DEFINED automáticamente como situação
        patient.setPatientSituation(PatientSituation.NOT_DEFINED);
        // 3. PERSISTÊNCIA
        var savedPatient = repository.save(patient);
        // ---------------------------------------------
        // ENVIO DO EVENTO ASSÍNCRONO
        // ---------------------------------------------
        // 4. Criando o objeto de evento
        userProducer.publishUserCreationToPatientEvent(savedPatient);
        return mapper.toPatientResponseDTO(savedPatient);
    }
    /**
     * Busca um paciente pelo seu identificador único.
     *
     * @param id O ID do paciente a ser procurado.
     * @return A entidade {@link Patient} encontrada.
     * @throws NoSuchElementException Se nenhum paciente for encontrado com o ID fornecido.
     */
    public Patient findById(long id){
        return repository.findById(id).orElseThrow(() -> new NoSuchElementException("NO DATA FOUND"));
    }
    /**
     * Retorna uma lista contendo todos os pacientes cadastrados no sistema.
     *
     * @return Uma {@link List} de entidades {@link Patient}. Pode ser uma lista vazia,
     * mas nunca {@code null}.
     */
    public List<Patient> findAll(){
        List<Patient> patients = repository.findAll();
        
        return patients;
    }
}
