package com.kelwin.assistente_financeiro.presentation.dto;

import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Resposta padrão para erros da API")
public record ResponseError(

    @Schema(description = "Título do erro", example = "Transação não encontrada")
    String titulo,

    @Schema(description = "Detalhes do erro", example = "A transação informada não foi encontrada.")
    String detalhes,

    @Schema(description = "Data e hora em que o erro ocorreu", example = "2026-09-13T17:30:00")
    LocalDateTime horario

) {}