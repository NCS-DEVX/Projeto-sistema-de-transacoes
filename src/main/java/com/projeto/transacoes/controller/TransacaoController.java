package com.projeto.transacoes.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.projeto.transacoes.model.Cliente;
import com.projeto.transacoes.model.Empresa;
import com.projeto.transacoes.model.Transacao;
import com.projeto.transacoes.service.ClienteService;
import com.projeto.transacoes.service.EmpresaService;
import com.projeto.transacoes.service.TransacaoService;

@RestController
@RequestMapping("/transacoes")
public class TransacaoController {

    @Autowired
    private TransacaoService transacaoService;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private EmpresaService empresaService;

    @GetMapping
    public List<Transacao> listarTodas() {
        return transacaoService.listarTodas();
    }

    @PostMapping
    public ResponseEntity<Transacao> processarTransacao(
        @RequestParam Long clienteId,
        @RequestParam Long empresaId,
        @RequestParam Double valor,
        @RequestParam String tipo
    ) {
        Cliente cliente = clienteService.buscarPorId(clienteId)
            .orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado"));
        Empresa empresa = empresaService.buscarPorId(empresaId)
            .orElseThrow(() -> new IllegalArgumentException("Empresa não encontrada"));

        Transacao transacao = transacaoService.processarTransacao(cliente, empresa, valor, tipo);
        return ResponseEntity.ok(transacao);
    }
}
