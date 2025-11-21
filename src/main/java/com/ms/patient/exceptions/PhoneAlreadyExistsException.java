package com.ms.patient.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exceção lançada quando uma tentativa de criação ou atualização de um registro de Pessoa
 * utiliza um **número de telefone que já está cadastrado** no sistema, violando a regra de unicidade.
 *
 * <p>Esta exceção é mapeada para o status HTTP **409 Conflict** (Conflito)
 * através da anotação {@code @ResponseStatus(HttpStatus.CONFLICT)}, indicando que
 * a requisição não pôde ser processada devido a um conflito de dados existentes.</p>
 */
@ResponseStatus(HttpStatus.CONFLICT)
public class PhoneAlreadyExistsException extends RuntimeException{

    /**
     * Construtor padrão que cria a exceção com uma mensagem fixa.
     *
     * <p>A mensagem padrão é: "O telefone informado já está em uso".</p>
     */
    public PhoneAlreadyExistsException() {
        super("O telefone informado já está em uso");
    }
}