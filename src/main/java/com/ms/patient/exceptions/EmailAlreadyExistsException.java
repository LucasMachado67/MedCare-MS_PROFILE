package com.ms.patient.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exceção lançada quando uma tentativa de criação ou atualização de um registro de Pessoa
 * utiliza um **endereço de e-mail que já está cadastrado** no sistema, violando a regra de unicidade.
 *
 * <p>Esta exceção é mapeada para o status HTTP **409 Conflict** (Conflito)
 * através da anotação {@code @ResponseStatus(HttpStatus.CONFLICT)}, indicando que
 * a requisição não pôde ser processada devido a um conflito de dados existente.</p>
 */
@ResponseStatus(HttpStatus.CONFLICT)
public class EmailAlreadyExistsException extends RuntimeException{

    /**
     * Construtor que cria a exceção.
     *
     * @param message A mensagem de erro detalhada, geralmente indicando qual e-mail causou o conflito.
     */
    public EmailAlreadyExistsException(String message) {
        super(message);
    }
}