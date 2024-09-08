package com.projeto.transacoes.validators;

public class CpfValidator {

    public static boolean isValid(String cpf) {
        // Remover caracteres não numéricos
        cpf = cpf.replaceAll("[^\\d]", "");

        // Verificar se a string tem 11 dígitos
        if (cpf.length() != 11) {
            return false;
        }

        // Verificar se todos os dígitos são iguais (CPF inválido)
        if (cpf.chars().distinct().count() == 1) {
            return false;
        }

        // Validar dígitos verificadores
        int[] cpfArray = cpf.chars().map(c -> c - '0').toArray();
        int sum1 = 0, sum2 = 0;

        for (int i = 0; i < 9; i++) {
            sum1 += cpfArray[i] * (10 - i);
        }

        int checkDigit1 = (sum1 * 10) % 11;
        if (checkDigit1 == 10) checkDigit1 = 0;

        if (checkDigit1 != cpfArray[9]) {
            return false;
        }

        for (int i = 0; i < 10; i++) {
            sum2 += cpfArray[i] * (11 - i);
        }

        int checkDigit2 = (sum2 * 10) % 11;
        if (checkDigit2 == 10) checkDigit2 = 0;

        return checkDigit2 == cpfArray[10];
    }
}
