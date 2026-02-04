package com.ms.patient.utils;

import java.util.InputMismatchException;

/**
 * Classe de utilidade (Utility Class) contendo métodos estáticos para
 * realizar a validação matemática e de formato do Cadastro de Pessoas Físicas (CPF) brasileiro.
 *
 * <p>Esta validação segue a regra dos dígitos verificadores (DV1 e DV2).</p>
 */
public class CpfValidatorUtils {

    // Prevenindo instanciação da classe utilitária
    private CpfValidatorUtils() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * Remove a máscara e verifica a validade matemática de um CPF.
     *
     * <p>Executa as seguintes etapas:
     * <ul>
     * <li>Limpa a string, removendo caracteres não numéricos.</li>
     * <li>Verifica o tamanho (deve ter 11 dígitos).</li>
     * <li>Bloqueia CPFs com todos os dígitos iguais (ex: 111.111.111-11).</li>
     * <li>Calcula o primeiro e o segundo dígito verificador e compara com os dígitos fornecidos.</li>
     * </ul></p>
     *
     * @param cpf O CPF a ser validado, podendo conter máscara (pontos e traços).
     * @return {@code true} se o CPF for válido conforme as regras matemáticas e de formato, {@code false} caso contrário.
     */
    public static boolean isValidCpf(String cpf) {
        if (cpf == null) {
            return false;
        }

        // 1. Limpeza: Remove a máscara (deixa apenas dígitos)
        String cleanCpf = cpf.replaceAll("[^0-9]", "");

        // 2. Validação de Tamanho
        if (cleanCpf.length() != 11) {
            return false;
        }

        // 3. Validação de Sequência (CPFs com todos os dígitos iguais são inválidos)
        if (cleanCpf.matches("(\\d)\\1{10}")) {
            return false;
        }

        try {
            // Extrai os 9 primeiros dígitos (Base)
            long cpfBase = Long.parseLong(cleanCpf.substring(0, 9));
            // Extrai os 2 dígitos verificadores fornecidos
            int providedDv1 = Integer.parseInt(cleanCpf.substring(9, 10));
            int providedDv2 = Integer.parseInt(cleanCpf.substring(10, 11));

            // Calcula o primeiro DV
            int calculatedDv1 = calculateDv(String.valueOf(cpfBase), 10);

            // Se o primeiro não bate, já é inválido
            if (calculatedDv1 != providedDv1) {
                return false;
            }

            // Calcula o segundo DV (usa os 9 dígitos base + o primeiro DV calculado)
            int calculatedDv2 = calculateDv(String.valueOf(cpfBase) + calculatedDv1, 11);

            // Verifica se o segundo DV bate
            return calculatedDv2 == providedDv2;

        } catch (NumberFormatException | InputMismatchException e) {
            // Caso ocorra algum erro de parsing, o CPF é inválido
            return false;
        }
    }

    /**
     * Método auxiliar privado para calcular um dígito verificador (DV) usando o algoritmo de módulo 11.
     *
     * <p>O cálculo envolve a soma dos produtos dos dígitos da base por pesos decrescentes
     * (começando pelo peso fornecido). O DV é (11 - (soma % 11)).</p>
     *
     * @param base String contendo os dígitos anteriores ao DV (9 dígitos para o primeiro DV, 10 para o segundo).
     * @param peso O peso inicial para a multiplicação (10 para o primeiro DV, 11 para o segundo).
     * @return O dígito verificador calculado (valor entre 0 e 9).
     */
    private static int calculateDv(String base, int peso) {
        int soma = 0;
        int num;

        // Itera sobre os dígitos da base (da esquerda para a direita)
        for (int i = 0; i < base.length(); i++) {
            // Multiplica o dígito pelo peso decrescente
            num = Integer.parseInt(base.substring(i, i + 1));
            soma += num * peso;
            peso--;
        }

        // 1. Encontra o resto da divisão por 11
        int resto = soma % 11;

        // 2. Se o resto for menor que 2, o DV é 0
        if (resto < 2) {
            return 0;
        } else {
            // 3. Caso contrário, o DV é (11 - resto)
            return 11 - resto;
        }
    }
}