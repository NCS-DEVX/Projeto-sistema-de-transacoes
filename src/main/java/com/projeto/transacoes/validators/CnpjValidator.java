package com.projeto.transacoes.validators;

public class CnpjValidator {

    public static boolean isValid(String cnpj) {
        // Remover caracteres não numéricos
        cnpj = cnpj.replaceAll("[^\\d]", "");

        // Verificar se a string tem 14 dígitos
        if (cnpj.length() != 14) {
            return false;
        }

        // Verificar se todos os dígitos são iguais (CNPJ inválido)
        if (cnpj.chars().distinct().count() == 1) {
            return false;
        }

        int[] cnpjArray = cnpj.chars().map(c -> c - '0').toArray();
        int sum1 = 0, sum2 = 0;

        int[] weights1 = {5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};
        int[] weights2 = {6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};

        for (int i = 0; i < 12; i++) {
            sum1 += cnpjArray[i] * weights1[i];
            sum2 += cnpjArray[i] * weights2[i];
        }

        int checkDigit1 = (sum1 % 11 < 2) ? 0 : 11 - (sum1 % 11);
        if (checkDigit1 != cnpjArray[12]) {
            return false;
        }

        sum2 += checkDigit1 * weights2[12];
        int checkDigit2 = (sum2 % 11 < 2) ? 0 : 11 - (sum2 % 11);

        return checkDigit2 == cnpjArray[13];
    }
}

