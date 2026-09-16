package com.kelwin.assistente_financeiro.presentation.controller;

import org.springframework.web.bind.annotation.RestController;

import com.kelwin.assistente_financeiro.application.service.AssistenteVozService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@RestController
@RequestMapping("/assistente")
public class AssistenteController {

    private final AssistenteVozService service;

    @Operation(
        summary = "Enviar mensagem para o assistente",
        description = "Envia uma mensagem em linguagem natural para o assistente financeiro. "
                    + "A IA pode utilizar as ferramentas disponíveis para consultar informações financeiras."
    )
    @ApiResponses({
        @ApiResponse(
            responseCode = "200",
            description = "Resposta gerada com sucesso",
            content = @Content(
                mediaType = "text/plain",
                schema = @Schema(
                    type = "string",
                    example = "Você possui uma transação de R$ 35,90 referente a Almoço."
                )
            )
        ),
        @ApiResponse(
            responseCode = "500",
            description = "Erro ao processar a solicitação"
        )
    })
    @PostMapping
    public String respostaIA(
            @RequestBody
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                description = "Mensagem em linguagem natural enviada ao assistente.",
                required = true,
                content = @Content(
                    mediaType = "text/plain",
                    schema = @Schema(
                        type = "string",
                        example = "Quais são minhas transações?"
                    )
                )
            )
            String mensagem) {

        return service.respostaIA(mensagem);
    }
}