package com.kelwin.assistente_financeiro.presentation.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.kelwin.assistente_financeiro.domain.model.TipoTransacao;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Dados de uma transação financeira")
public record TransacaoResponse(

    @Schema(description = "Identificador da transação", example = "1")
    Long id,

    @Schema(description = "Descrição da transação", example = "Compra no supermercado")
    String descricao,

    @Schema(description = "Valor da transação", example = "150.50")
    BigDecimal valor,

    @Schema(description = "Tipo da transação", example = "DESPESA")
    TipoTransacao tipo,

    @Schema(description = "Data e hora da transação", example = "2026-09-13T15:30:00")
    LocalDateTime data

) {}