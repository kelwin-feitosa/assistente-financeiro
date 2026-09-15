package com.kelwin.assistente_financeiro.application.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class AssistenteVozService {
    private final ChatClient chatClient;

    public String respostaIA(String mensagem) {
        return chatClient.prompt()
            .user(mensagem)
            .call()
            .content();
    }
} 
