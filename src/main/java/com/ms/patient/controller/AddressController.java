package com.ms.patient.controller;

import java.net.URI;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.ms.patient.service.AddressService;
import com.ms.patient.dto.AddressDTO;
import com.ms.patient.dto.AddressResponseDTO;
import com.ms.patient.mappers.AddressMapper;
import com.ms.patient.models.Address;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

/**
 * Controlador REST para gerenciar operações CRUD (Criação, Leitura, Atualização e Deleção)
 * da entidade Endereço (Address).
 *
 * <p>Mapeado para o caminho base "/address".</p>
 *
 * @author Lucas Edson Machado
 * @since 2025-11-17
 */
@RestController
@RequestMapping("address")
public class AddressController {

    @Autowired
    private AddressService service;

    @Autowired
    private AddressMapper mapper;

    /**
     * Cria um novo endereço no sistema.
     *
     * <p>Recebe os dados do endereço no corpo da requisição (payload) e retorna
     * o status HTTP 201 (Created) junto com a URI do novo recurso.</p>
     *
     * @param addressDTO O objeto de transferência de dados (DTO) contendo as
     * informações do novo endereço. Deve ser formatado como JSON.
     * @return ResponseEntity contendo o {@link AddressResponseDTO} do endereço criado
     * e o status HTTP 201 (Created).
     * @throws jakarta.validation.ValidationException se o DTO não for válido.
     * @throws Exception para erros internos não tratados (retorna 500 Internal Server Error).
     */
    @PostMapping("/create")
    public ResponseEntity<AddressResponseDTO> createAddress(@Valid @RequestBody AddressDTO addressDTO) {
        try {
            //retornar a Entidade SALVA
            Address entityAddress = service.createAddress(addressDTO);
            //CONSTRUÇÃO DA URI: Usa o ID do objeto salvo para criar o link do novo recurso
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(entityAddress.getId())
                    .toUri();
            //Mapeando a Entidade salva para o responseDto
            AddressResponseDTO responseDTO = mapper.toDtoResponse(entityAddress);
            //Retorna 201 Created com a URI
            return ResponseEntity.created(location).body(responseDTO);

        } catch (Exception e) {
           return ResponseEntity.internalServerError().build();
        }
    }
    /**
     * Busca os detalhes de um endereço específico pelo seu ID.
     *
     * @param id O identificador único do endereço a ser buscado.
     * @return ResponseEntity contendo o {@link AddressResponseDTO} correspondente
     * e o status HTTP 200 (OK).
     * @throws ResponseEntity.NotFound() Se nenhum endereço for encontrado com o ID fornecido (mapeado para 404).
     */
    @GetMapping("/{id}")
    public ResponseEntity<AddressResponseDTO> findAddressById(@PathVariable long id) {
        //Pegando o objeto address do service
        Address entity = service.findAddressById(id);
        if(entity == null){
            return ResponseEntity.notFound().build();
        }
        //Transformando em DTO
        AddressResponseDTO responseDto = mapper.toDtoResponse(entity);
        //mandando o DTO
        return ResponseEntity.ok(responseDto);
    }
    /**
     * Lista todos os endereços cadastrados no sistema.
     *
     * @return ResponseEntity contendo uma {@link List} de {@link AddressResponseDTO}s
     * e o status HTTP 200 (OK).
     */
    @GetMapping("/all")
    public ResponseEntity<List<AddressResponseDTO>> findAll() {

        //Pegando a lista de address com o service
        List<Address> addresses = service.findAll();
        //Transformando a lista em responseDto
        List<AddressResponseDTO> addressResponses = mapper.toDtoResponse(addresses);
        //Retornando response
        return ResponseEntity.ok(addressResponses);
        
    }
    /**
     * Atualiza um endereço existente com base no ID.
     *
     * @param id O ID do endereço a ser atualizado.
     * @param addressDTO O DTO contendo os dados a serem atualizados (no corpo da requisição).
     * @return ResponseEntity contendo o {@link AddressResponseDTO} atualizado e o status HTTP 200 (OK).
     * @throws NoSuchElementException Se o ID não for encontrado (mapeado para 404).
     * @throws jakarta.validation.ValidationException se o DTO não for válido.
     */
    @PutMapping("/{id}")
    public ResponseEntity<AddressResponseDTO> updateAddress(@PathVariable long id, @Valid @RequestBody AddressDTO addressDTO) {

        //Controller chama o Service para executar o update
        Address updatedEntity = service.updateAddress(id, addressDTO);
        //Mapeia a Entidade atualizada para o DTO de Resposta
        AddressResponseDTO responseDTO = mapper.toDtoResponse(updatedEntity);
        //Retorna 200 OK e o corpo atualizado
        return ResponseEntity.ok(responseDTO);
    }
    /**
     * Deleta um endereço do sistema pelo seu ID.
     *
     * @param id O ID do endereço a ser deletado.
     * @return ResponseEntity com o status HTTP 204 (No Content), indicando sucesso na deleção sem corpo de resposta.
     * @throws NoSuchElementException Se o ID não for encontrado (mapeado para 404) vindo do service
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAddress(@PathVariable long id){

        service.deleteAddress(id);

        return ResponseEntity.noContent().build();
    }
}
