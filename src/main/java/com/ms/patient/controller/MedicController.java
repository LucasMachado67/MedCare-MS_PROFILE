package com.ms.patient.controller;

import com.ms.patient.dto.MedicCreationDTO;
import com.ms.patient.dto.MedicResponseDTO;
import com.ms.patient.service.MedicService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/medics")
public class MedicController {


    private final MedicService service;

    public MedicController(MedicService service){
        this.service = service;
    }
    /**
     * Endpoint para registrar um novo Médico.
     * @param dto Os dados do médico, incluindo dados da Pessoa e Endereço.
     * @return O DTO do Médico criado com seus IDs.
     */
    @PostMapping
    public ResponseEntity<MedicResponseDTO> registerMedic(@RequestBody @Valid MedicCreationDTO dto) {

        // 1. Delega a lógica para o Service (que salva no DB e envia o evento)
        MedicResponseDTO responseDTO = service.createMedic(dto);

        // 2. Retorna 201 Created (padrão HTTP para criação bem-sucedida)
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(responseDTO);
    }
}
