package com.projeto.transacoes.validators;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CnpjValidatorTest {

    @Test
    public void testValidCnpj() {
        assertTrue(CnpjValidator.isValid("12.345.678/0001-95")); // Exemplo de CNPJ válido
    }

    @Test
    public void testInvalidCnpj() {
        assertFalse(CnpjValidator.isValid("12.345.678/0001-00")); // Exemplo de CNPJ inválido
    }
}
