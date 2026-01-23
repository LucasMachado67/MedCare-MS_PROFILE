package com.ms.patient.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ms.patient.dto.AddressDTO;
import com.ms.patient.mappers.AddressMapper;
import com.ms.patient.models.Address;
import com.ms.patient.repositories.AddressRepository;

/**
 * Serviço responsável por orquestrar a lógica de negócio (CRUD)
 * para a entidade {@link Address}.
 *
 * <p>Esta classe gerencia a persistência de endereços, conversão de DTOs
 * e validação de existência de recursos.</p>
 *
 * @author Lucas Edson Machado
 * @since 2025-11-17
 */
@Service
public class AddressService {

    @Autowired
    private AddressRepository repository;

    @Autowired
    private AddressMapper mapper;

    /**
     * Cria e persiste um novo endereço no banco de dados.
     *
     * @param addressDTO O DTO contendo as informações do endereço a ser criado.
     * @return A entidade {@link Address} recém-salva com o ID gerado.
     */
    public Address createAddress(AddressDTO addressDTO){

        Address address = mapper.toEntityCreation(addressDTO);
        return repository.save(address);
    }

    /**
     * Busca um endereço pelo seu identificador único.
     *
     * @param id O ID do endereço a ser procurado.
     * @return A entidade {@link Address} encontrada.
     * @throws NoSuchElementException Se nenhum endereço for encontrado com o ID fornecido.
     */
    public Address findAddressById(Long id){
        return repository.findById(id)
            .orElseThrow(() -> new NoSuchElementException("NOT FOUND"));
    }

    /**
     * Retorna uma lista contendo todos os endereços cadastrados no sistema.
     *
     * @return Uma {@link List} de entidades {@link Address}. Pode ser uma lista vazia,
     * mas nunca {@code null}.
     */
    public List<Address> findAll(){
        return repository.findAll();
    }

    /**
     * Atualiza um endereço existente com base no ID e nos dados fornecidos no DTO.
     *
     * <p>Primeiro busca o endereço existente, aplica as mudanças do DTO utilizando o Mapper,
     * e então persiste a entidade atualizada.</p>
     *
     * @param id O ID do endereço a ser atualizado.
     * @param addressDTO O DTO contendo os novos dados do endereço.
     * @return A entidade {@link Address} atualizada.
     * @throws NoSuchElementException Se nenhum endereço for encontrado com o ID fornecido
     * (lançada por {@link #findAddressById(Long)}).
     */
    public Address updateAddress(long id, AddressDTO addressDTO){

        Address existingAddress = findAddressById(id);

        mapper.updateAddressFromDto(addressDTO, existingAddress);

        return repository.save(existingAddress);
        
    }

    /**
     * Deleta permanentemente um endereço do sistema pelo seu ID.
     *
     * <p>
     * Nota: O uso de {@code .get()} para buscar antes de deletar pode ser perigoso
     * se o ID não existir, lançando {@link java.util.NoSuchElementException}.
     * </p>
     *
     * @param id O ID do endereço a ser deletado.
     * @throws NoSuchElementException se o id não for encontrado
     */
    public void deleteAddress(long id){

        Address addressToDelete = repository.findById(id).orElseThrow(() -> new NoSuchElementException("Sem dados"));
        
        repository.delete(addressToDelete);
    }

    
}
