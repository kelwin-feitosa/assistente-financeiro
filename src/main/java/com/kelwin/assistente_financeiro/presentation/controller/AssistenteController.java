package com.kelwin.assistente_financeiro.presentation.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.kelwin.assistente_financeiro.application.service.AssistenteVozService;
import com.kelwin.assistente_financeiro.application.service.PiperService;
import com.kelwin.assistente_financeiro.application.service.WhisperService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequiredArgsConstructor
@RestController
@RequestMapping("/assistente")
public class AssistenteController {

    private final AssistenteVozService service;
    private final WhisperService whisperService;
    private final PiperService piperService;

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

    @PostMapping(
        value = "/voz",
        consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
        produces = MediaType.APPLICATION_OCTET_STREAM_VALUE
    )
    public ResponseEntity<byte[]> respostaPorVoz(
            @RequestParam("audio") MultipartFile audio)
            throws IOException, InterruptedException {

        Path arquivoTemporario = Files.createTempFile("assistente-", ".wav");
        Path audioResposta = null;

        try {
            audio.transferTo(arquivoTemporario);

            String mensagem = whisperService.transcrever(arquivoTemporario);

            String resposta = service.respostaIA(mensagem);

            audioResposta = piperService.sintetizar(resposta);

            byte[] audioBytes = Files.readAllBytes(audioResposta);

            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .body(audioBytes);

        } finally {
            Files.deleteIfExists(arquivoTemporario);

            if (audioResposta != null) {
                Files.deleteIfExists(audioResposta);
            }
        }
    }
}