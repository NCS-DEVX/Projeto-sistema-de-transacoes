package com.projeto.transacoes.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender emailSender;

    public void enviarEmail(String para, String assunto, String corpo) {
        SimpleMailMessage mensagem = new SimpleMailMessage();
        mensagem.setTo(para);
        mensagem.setSubject(assunto);
        mensagem.setText(corpo);
        try {
            emailSender.send(mensagem);
            System.out.println("E-mail enviado com sucesso.");
        } catch (MailException e) {
            System.out.println("Erro ao enviar e-mail: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Erro inesperado ao enviar e-mail: " + e.getMessage());
        }
    }
}
