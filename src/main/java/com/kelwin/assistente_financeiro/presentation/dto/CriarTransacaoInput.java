package com.kelwin.assistente_financeiro.presentation.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.ai.tool.annotation.ToolParam;

import com.kelwin.assistente_financeiro.domain.model.TipoTransacao;

public record CriarTransacaoInput(
    @ToolParam(description = "Descrição da transação")
    String descricao,

    @ToolParam(description = "Valor da transação em reais")
    BigDecimal valor,

    @ToolParam(description = "Tipo da transação: RECEITA ou DESPESA")
    TipoTransacao tipo,

    @ToolParam(description = "Data e hora da transação. Pode ser nula se o usuário não informar uma data.")
    LocalDateTime data
) {

}
