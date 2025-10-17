package com.ms.patient.exceptions;

public class InvalidCpfException extends RuntimeException{

    public InvalidCpfException(String cpf){
        super(cpf);
    }
}
