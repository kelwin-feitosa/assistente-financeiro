package com.kelwin.assistente_financeiro.presentation.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.kelwin.assistente_financeiro.domain.model.TipoTransacao;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Schema(description = "Dados necessários para criar uma transação financeira")
public record TransacaoRequest(

    @Schema(
        description = "Descrição da transação",
        example = "Compra no supermercado"
    )
    @NotBlank(message = "A descrição é obrigatória")
    @Size(max = 255, message = "A descrição deve ter no máximo 255 caracteres")
    String descricao,

    @Schema(
        description = "Valor da transação",
        example = "150.50"
    )
    @NotNull(message = "O valor é obrigatório")
    @DecimalMin(value = "0.01", message = "O valor deve ser maior que zero")
    BigDecimal valor,

    @Schema(
        description = "Tipo da transação",
        example = "DESPESA"
    )
    @NotNull(message = "O tipo da transação é obrigatório")
    TipoTransacao tipo,

    @Schema(
        description = "Data e hora da transação",
        example = "2026-09-13T15:30:00"
    )
    LocalDateTime data

) {}