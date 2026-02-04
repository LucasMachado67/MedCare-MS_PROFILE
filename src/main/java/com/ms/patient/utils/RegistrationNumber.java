package com.ms.patient.utils;

import java.util.Random;

public class RegistrationNumber {
    private static final Random random = new Random();

    public String generateNumber(){

        StringBuilder generatedNumber = new StringBuilder();
        for (int i = 0; i < 9; i++) {
            // Gera um número
            int numero = random.nextInt(10);
            generatedNumber.append(String.valueOf(numero));
        }

        return generatedNumber.toString();
    }

}
