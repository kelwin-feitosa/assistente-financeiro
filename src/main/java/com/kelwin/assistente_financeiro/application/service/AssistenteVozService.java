package com.kelwin.assistente_financeiro.application.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

import com.kelwin.assistente_financeiro.infrastructure.ai.tools.ConsultarTransacoesTool;
import com.kelwin.assistente_financeiro.infrastructure.ai.tools.CriarTransacaoTool;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class AssistenteVozService {
    private final ChatClient chatClient;
    private final ConsultarTransacoesTool consultarTransacoesTool;
    private final CriarTransacaoTool criarTransacaoTool;

    public String respostaIA(String mensagem) {
        return chatClient.prompt()
            .tools(consultarTransacoesTool, criarTransacaoTool)
            .user(mensagem)
            .call()
            .content();
    }
} 
