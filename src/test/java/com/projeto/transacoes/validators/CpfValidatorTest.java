package com.projeto.transacoes.validators;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CpfValidatorTest {

    @Test
    public void testValidCpf() {
        assertTrue(CpfValidator.isValid("123.456.789-09")); // Exemplo de CPF válido
    }

    @Test
    public void testInvalidCpf() {
        assertFalse(CpfValidator.isValid("123.456.789-00")); // Exemplo de CPF inválido
    }
}
