package com.projeto.transacoes.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Service
public class CallbackService {

    @Value("${callback.url}")
    private String callbackUrl; // URL para enviar o callback

    private final RestTemplate restTemplate;

    public CallbackService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void enviarCallback(String mensagem) {
        try {
            ResponseEntity<String> response = restTemplate.postForEntity(callbackUrl, mensagem, String.class);
            if (response.getStatusCode().is2xxSuccessful()) {
                System.out.println("Callback enviado com sucesso.");
            } else {
                System.out.println("Falha ao enviar callback: " + response.getStatusCode());
            }
        } catch (RestClientException e) {
            System.out.println("Erro ao enviar callback: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Erro inesperado ao enviar callback: " + e.getMessage());
        }
    }
}
