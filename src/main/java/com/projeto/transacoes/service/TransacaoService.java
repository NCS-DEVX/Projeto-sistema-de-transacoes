package com.projeto.transacoes.service;

import com.projeto.transacoes.model.Cliente;
import com.projeto.transacoes.model.Empresa;
import com.projeto.transacoes.model.Transacao;
import com.projeto.transacoes.repository.TransacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TransacaoService {

    @Autowired
    private TransacaoRepository transacaoRepository;

    @Autowired
    private EmpresaService empresaService;

    @Autowired
    private CallbackService callbackService;

    @Autowired
    private EmailService emailService;

    public List<Transacao> listarTodas() {
        return transacaoRepository.findAll();
    }

    public Transacao processarTransacao(Cliente cliente, Empresa empresa, Double valor, String tipo) {
        Transacao transacao = new Transacao();
        transacao.setCliente(cliente);
        transacao.setEmpresa(empresa);
        transacao.setValor(valor);
        transacao.setDataHora(LocalDateTime.now());
        transacao.setTipo(tipo);

        try {
            if ("DEPOSITO".equalsIgnoreCase(tipo)) {
                empresa.setSaldo(empresa.getSaldo() + valor);
            } else if ("SAQUE".equalsIgnoreCase(tipo) && empresa.getSaldo() >= valor) {
                empresa.setSaldo(empresa.getSaldo() - valor);
            } else {
                throw new IllegalArgumentException("Saldo insuficiente ou operação inválida.");
            }

            empresaService.salvar(empresa); // Atualizar o saldo da empresa
            Transacao transacaoSalva = transacaoRepository.save(transacao); // Salvar a transação
            
            // Enviar callback
            callbackService.enviarCallback("Transação de " + tipo + " no valor de " + valor + " foi realizada.");

            // Enviar notificação por e-mail
            emailService.enviarEmail(cliente.getEmail(), "Notificação de Transação", "Uma transação de " + tipo + " no valor de " + valor + " foi realizada.");

            return transacaoSalva;
        } catch (IllegalArgumentException e) {
            System.out.println("Erro ao processar transação: " + e.getMessage());
            throw e; // Re-throw to handle this error further up the stack if needed
        } catch (Exception e) {
            System.out.println("Erro inesperado ao processar transação: " + e.getMessage());
            throw e; // Re-throw to handle this error further up the stack if needed
        }
    }
}
