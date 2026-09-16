package com.kelwin.assistente_financeiro.infrastructure.ai.tools;

import java.time.LocalDateTime;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Component;

import com.kelwin.assistente_financeiro.application.service.TransacaoService;
import com.kelwin.assistente_financeiro.presentation.dto.CriarTransacaoInput;
import com.kelwin.assistente_financeiro.presentation.dto.TransacaoRequest;
import com.kelwin.assistente_financeiro.presentation.dto.TransacaoResponse;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor 
@Component 
public class CriarTransacaoTool {
    private final TransacaoService service;

    @Tool(
        name = "criar-transacao",
        description = """
            Cria e registra uma nova transação financeira.
            Use esta ferramenta quando o usuário informar uma nova receita ou despesa.

            Se o usuário não informar uma data, deixe o campo data como null.
            Nesse caso, o sistema utilizará automaticamente a data e hora atuais.
            Não invente ou estime uma data.
            """
    )
    public TransacaoResponse criarTransacao(CriarTransacaoInput input) {

        LocalDateTime data = input.data();

        if (data == null) {
            data = LocalDateTime.now();
        }

        var request = new TransacaoRequest(
            input.descricao(),
            input.valor(),
            input.tipo(),
            data
        );

        return service.criarTransacao(request);
    }
}
