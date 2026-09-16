package com.kelwin.assistente_financeiro.infrastructure.ai.tools;

import java.util.List;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Component;

import com.kelwin.assistente_financeiro.application.service.TransacaoService;
import com.kelwin.assistente_financeiro.presentation.dto.TransacaoResponse;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor 
@Component 
public class ConsultarTransacoesTool {

    private final TransacaoService service;

    @Tool(description = "Consulta todas as transações financeiras cadastradas.")
    public List<TransacaoResponse> consultarTransacoes() {
        return service.listarTransacoes();
    }
}
