package com.projeto.transacoes.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projeto.transacoes.model.Empresa;
import com.projeto.transacoes.repository.EmpresaRepository;
import com.projeto.transacoes.validators.CnpjValidator; // Importa o validador de CNPJ

@Service
public class EmpresaService {

    @Autowired
    private EmpresaRepository empresaRepository;

    public List<Empresa> listarTodas() {
        return empresaRepository.findAll();
    }

    public Optional<Empresa> buscarPorId(Long id) {
        return empresaRepository.findById(id);
    }

    public Empresa salvar(Empresa empresa) {
        if (!CnpjValidator.isValid(empresa.getCnpj())) {
            throw new IllegalArgumentException("CNPJ inválido");
        }
        return empresaRepository.save(empresa);
    }

    public void deletar(Long id) {
        empresaRepository.deleteById(id);
    }
}
