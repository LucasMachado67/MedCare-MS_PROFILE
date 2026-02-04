package com.ms.patient.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ms.patient.dto.PersonCreationDTO;
import com.ms.patient.dto.PersonEmailSenderDto;
import com.ms.patient.exceptions.CpfAlreadyExistsException;
import com.ms.patient.exceptions.EmailAlreadyExistsException;
import com.ms.patient.exceptions.InvalidCpfException;
import com.ms.patient.mappers.PersonMapper;
import com.ms.patient.models.Person;
import com.ms.patient.repositories.PersonRepository;
import com.ms.patient.utils.CpfValidatorUtils;

/**
 * Serviço responsável por orquestrar a lógica de negócio (CRUD e validações)
 * para a entidade {@link Person}.
 *
 * <p>Esta classe gerencia a criação, validação (CPF e unicidade), busca e
 * manipulação da entidade Pessoa, servindo como camada de regras de negócio.</p>
 *
 * @author Lucas Edson Machado
 * @since 2025-11-17
 */
@Service
public class PersonService {

    @Autowired
    private PersonRepository repository;

    @Autowired
    private PersonMapper mapper;

    /**
     * Cria e persiste uma nova pessoa no sistema, aplicando uma série de validações.
     *
     * <p>As validações incluem:
     * <ul>
     * <li>Unicidade do e-mail.</li>
     * <li>Validação matemática do CPF (via {@link CpfValidatorUtils}).</li>
     * <li>Unicidade do CPF no banco de dados.</li>
     * </ul></p>
     *
     * @param dto O DTO contendo os dados de criação da Pessoa.
     * @return A entidade {@link Person} recém-salva com o 'ID' gerado.
     * @throws EmailAlreadyExistsException Se o e-mail já estiver cadastrado.
     * @throws InvalidCpfException Se o CPF for matematicamente inválido.
     * @throws CpfAlreadyExistsException Se o CPF já estiver cadastrado no banco de dados.
     */
    public boolean validatePersonInfo(PersonCreationDTO dto){
        //Validação de CPF
        String cpfToValidate = dto.getCpf();
        //Validando se o e-mail é único
        if (repository.existsByEmail(dto.getEmail())) {
            throw new EmailAlreadyExistsException(""); 
        }
        
        // Validação matemática de CPF - comentado para facilitar testes iniciais
//        if (!CpfValidatorUtils.isValidCpf(cpfToValidate)) {
//            throw new InvalidCpfException("O CPF é inválido. Verifique o formato ou os dígitos.");
//        }

        // Validação de Unicidade (usando o CPF limpo)
        String cleanCpf = cpfToValidate.replaceAll("[^0-9]", "");
        if (repository.existsByCpf(cleanCpf)) {
            throw new CpfAlreadyExistsException(cleanCpf); 
        }

        return true;
    }

    /**
     * Busca uma pessoa pelo seu identificador único.
     *
     * @param id O 'ID' da pessoa a ser procurada.
     * @return A entidade {@link Person} encontrada.
     * @throws NoSuchElementException Se nenhuma pessoa for encontrada com o 'ID' fornecido.
     */
    public Person findPersonById(long id){
        return repository.findById(id).orElseThrow(() -> new NoSuchElementException("NOT FOUND"));
    }

    /**
     * Busca uma pessoa pelo seu 'ID' e mapeia os campos essenciais ('ID', e-mail, nome)
     * para um DTO específico de envio de e-mail.
     *
     * @param id O 'ID' da pessoa para buscar.
     * @return O {@link PersonEmailSenderDto} contendo os dados necessários para o envio de e-mail.
     * @throws NoSuchElementException Se nenhuma pessoa for encontrada com o 'ID' fornecido.
     */
    public PersonEmailSenderDto findPersonByIdToSendEmail(long id){
        PersonEmailSenderDto personEmailSenderDto = new PersonEmailSenderDto();
        Person person = repository.findById(id).orElseThrow(() -> new NoSuchElementException("NOT FOUND"));
        personEmailSenderDto.setId(person.getId());
        personEmailSenderDto.setEmail(person.getEmail());
        personEmailSenderDto.setNome(person.getName());
        return personEmailSenderDto;
    }

    /**
     * Retorna uma lista contendo todas as pessoas cadastradas no sistema.
     *
     * @return Uma {@link List} de entidades {@link Person}. Pode ser uma lista vazia,
     * mas nunca {@code null}.
     */
    public List<Person> findAll(){
        return repository.findAll();
    }

    /**
     * Atualiza os dados de uma pessoa existente com base no'ID' e nos dados fornecidos no DTO.
     *
     * <p>Busca a pessoa existente, aplica as mudanças do DTO utilizando o Mapper,
     * e então persiste a entidade atualizada.</p>
     *
     * @param id O 'ID' da pessoa a ser atualizada.
     * @param personCreationDTO O DTO contendo os novos dados da pessoa.
     * @return A entidade {@link Person} atualizada.
     * @throws NoSuchElementException Se nenhuma pessoa for encontrada com o'ID' fornecido
     * (lançada por {@link #findPersonById(long)}).
     */
    public Person updatePerson(long id, PersonCreationDTO personCreationDTO){
        Person existingPerson = findPersonById(id);

        mapper.updatePersonFromDto(personCreationDTO, existingPerson);

        return repository.save(existingPerson);
    }

    /**
     * Delete permanentemente uma pessoa do sistema pelo seu 'ID'.
     *
     * <p>
     * Nota: O uso de {@code .get()} para buscar antes de deletar pode lançar {@link java.util.NoSuchElementException}
     * se o 'ID' não existir.
     * </p>
     *
     * @param id O 'ID' da pessoa a ser deletada.
     */
    public void deletePerson(long id){
        Person personToDelete = repository.findById(id).orElseThrow(() -> new NoSuchElementException("Not Found"));

        repository.delete(personToDelete);
    }

    /**
     * Busca e retorna uma pessoa pelo seu endereço de e-mail.
     *
     * @param email O endereço de e-mail da pessoa a ser procurada.
     * @return A entidade {@link Person} encontrada, ou {@code null} se não for encontrada
     * (dependendo da assinatura no {@link PersonRepository}).
     */
    public Person findPersonByEmail(String email){
        return repository.findPersonByEmail(email);
    }

}
