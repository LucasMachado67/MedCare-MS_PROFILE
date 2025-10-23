package com.ms.patient.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ms.patient.dto.PatientCreationDTO;
import com.ms.patient.dto.PatientResponseDTO;
import com.ms.patient.service.PatientService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("patients")
public class PatientController {

    @Autowired
    private PatientService service;
    /**
     * Endpoint para registrar um novo Médico.
     * @param dto Os dados do médico, incluindo dados da Pessoa e Endereço.
     * @return O DTO do Médico criado com seus IDs.
     */
    @PostMapping
    public ResponseEntity<PatientResponseDTO> registerPatient(@RequestBody @Valid PatientCreationDTO dto) {

        // 1. Delega a lógica para o Service (que salva no DB e envia o evento)
        PatientResponseDTO responseDTO = service.createPatient(dto);

        // 2. Retorna 201 Created (padrão HTTP para criação bem-sucedida)
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(responseDTO);
    }
}
