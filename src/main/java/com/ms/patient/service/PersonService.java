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



@Service
public class PersonService {

    @Autowired
    private PersonRepository repository;

    @Autowired
    private PersonMapper mapper;

    public Person createPerson(PersonCreationDTO dto){
        //Validação de CPF
        String cpfToValidate = dto.getCpf();
        
        //Validando se o email é unico
        if (repository.existsByEmail(dto.getEmail())) {
            throw new EmailAlreadyExistsException(""); 
        }
        
        // Validação matemática
        if (!CpfValidatorUtils.isValidCpf(cpfToValidate)) {
            throw new InvalidCpfException("O CPF é inválido. Verifique o formato ou os dígitos.");
        }

        // Validação de Unicidade (usando o CPF limpo)
        String cleanCpf = cpfToValidate.replaceAll("[^0-9]", "");
        if (repository.existsByCpf(cleanCpf)) {
            throw new CpfAlreadyExistsException(cleanCpf); 
        }


        Person person = mapper.toEntityCreation(dto);
        return repository.save(person);
    }

    public Person findPersonById(long id){
        return repository.findById(id).orElseThrow(() -> new NoSuchElementException("NOT FOUND"));
    }

    public PersonEmailSenderDto findPersonByIdToSendEmail(long id){
        PersonEmailSenderDto personEmailSenderDto = new PersonEmailSenderDto();
        Person person = repository.findById(id).orElseThrow(() -> new NoSuchElementException("NOT FOUND"));
        personEmailSenderDto.setId(person.getId());
        personEmailSenderDto.setEmail(person.getEmail());
        personEmailSenderDto.setNome(person.getName());
        return personEmailSenderDto;
    }


    public List<Person> findAll(){
        return repository.findAll();
    }

    public Person updatePerson(long id, PersonCreationDTO personCreationDTO){
        Person existingPerson = findPersonById(id);

        mapper.updatePersonFromDto(personCreationDTO, existingPerson);

        return repository.save(existingPerson);
    }

    public void deletePerson(long id){
        Person personToDelete = repository.findById(id).get();

        repository.delete(personToDelete);
    }

    public Person findPersonByEmail(String email){
        return repository.findPersonByEmail(email);
    }

}
