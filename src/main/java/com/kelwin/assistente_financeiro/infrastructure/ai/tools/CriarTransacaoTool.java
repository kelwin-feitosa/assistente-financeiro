package com.kelwin.assistente_financeiro.infrastructure.ai.tools;

import java.time.LocalDateTime;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Component;

import com.kelwin.assistente_financeiro.application.service.TransacaoService;
import com.kelwin.assistente_financeiro.presentation.dto.TransacaoRequest;
import com.kelwin.assistente_financeiro.presentation.dto.TransacaoResponse;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor 
@Component 
public class CriarTransacaoTool {
    private final TransacaoService service;

    @Tool(description = """
        Cria e registra uma nova transação financeira.

        Use esta ferramenta quando o usuário informar uma nova receita ou despesa.

        Parâmetros obrigatórios:
        - descricao: descrição da transação.
        - valor: valor da transação.
        - tipo: RECEITA ou DESPESA.

        Parâmetro opcional:
        - data: data e hora da transação.

        REGRA IMPORTANTE SOBRE A DATA:
        Se o usuário NÃO informar explicitamente uma data ou referência temporal,
        NÃO informe nenhum valor para o campo data.
        NÃO invente, estime ou utilize uma data padrão.
        Nesse caso, deixe o campo data como null.

        Se o usuário informar uma data, utilize somente a data informada pelo usuário.

        Retorna os dados da transação criada, incluindo o ID gerado pelo sistema.
        """)
    public TransacaoResponse criarTransacao(TransacaoRequest request) {

        LocalDateTime data = request.data();

        if(data == null) {
            data = LocalDateTime.now();
        }

        TransacaoRequest requestComData = new TransacaoRequest( //criar um novo dto, afinal ele é imutável
                request.descricao(),
                request.valor(),
                request.tipo(),
                data
        );

        return service.criarTransacao(requestComData);
    }
}
