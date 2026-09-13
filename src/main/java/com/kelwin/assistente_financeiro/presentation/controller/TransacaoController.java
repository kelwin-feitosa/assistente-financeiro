package com.kelwin.assistente_financeiro.presentation.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kelwin.assistente_financeiro.application.service.TransacaoService;
import com.kelwin.assistente_financeiro.presentation.dto.TransacaoRequest;
import com.kelwin.assistente_financeiro.presentation.dto.TransacaoResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/transacoes")
@Tag(name = "Transações", description = "Operações para gerenciamento de transações financeiras")
public class TransacaoController {

    private final TransacaoService service;

    @Operation(
        summary = "Criar uma transação",
        description = "Cria uma nova transação financeira."
    )
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Transação criada com sucesso"),
        @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    @PostMapping
    public ResponseEntity<TransacaoResponse> criarTransacao(
            @Valid @RequestBody TransacaoRequest request) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(service.criarTransacao(request));
    }

    @Operation(
        summary = "Listar transações",
        description = "Retorna todas as transações cadastradas."
    )
    @ApiResponse(responseCode = "200", description = "Transações retornadas com sucesso")
    @GetMapping
    public ResponseEntity<List<TransacaoResponse>> listarTransacoes() {
        return ResponseEntity.ok(service.listarTransacoes());
    }

    @Operation(
        summary = "Buscar transação",
        description = "Busca uma transação pelo seu identificador."
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Transação encontrada"),
        @ApiResponse(responseCode = "404", description = "Transação não encontrada")
    })
    @GetMapping("/{id}")
    public ResponseEntity<TransacaoResponse> buscarTransacao(
            @PathVariable Long id) {

        return ResponseEntity.ok(service.buscarTransacao(id));
    }

    @Operation(
        summary = "Excluir transação",
        description = "Exclui uma transação pelo seu identificador."
    )
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "Transação excluída com sucesso"),
        @ApiResponse(responseCode = "404", description = "Transação não encontrada")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarTransacao(
            @PathVariable Long id) {

        service.deletarTransacao(id);

        return ResponseEntity.noContent().build();
    }
}