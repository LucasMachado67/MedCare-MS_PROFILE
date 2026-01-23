package com.ms.patient.exceptions;

/**
 * Exceção lançada quando um CPF fornecido em uma requisição não passa nas validações
 * de formato ou nos critérios matemáticos (dígitos verificadores).
 *
 * <p>Esta exceção é tipicamente lançada na camada de Serviço (Service) após validação
 * e, quando tratada por um {@code @ControllerAdvice}, deve resultar no status HTTP
 * **400 Bad Request** (Requisição Inválida).</p>
 */
public class InvalidCpfException extends RuntimeException{

    /**
     * Construtor que cria a exceção.
     *
     * @param cpf O CPF inválido fornecido ou a mensagem de erro detalhada sobre a falha na validação.
     */
    public InvalidCpfException(String cpf){
        super(cpf);
    }
}