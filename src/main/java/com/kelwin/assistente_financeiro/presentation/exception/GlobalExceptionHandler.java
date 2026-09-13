package com.kelwin.assistente_financeiro.presentation.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.kelwin.assistente_financeiro.presentation.dto.ResponseError;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(TransacaoNaoEncontradaException.class)
    public ResponseEntity<ResponseError> tratarTransacaoNaoEncontrada(TransacaoNaoEncontradaException ex) {
        return resposta(
            HttpStatus.NOT_FOUND,
            "Transação não encontrada",
            ex.getMessage()
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseError> tratarErroValidacao(MethodArgumentNotValidException ex) {
        var campoErro = ex.getBindingResult().getFieldError();

        
        String mensagem = campoErro != null
                        ? campoErro.getDefaultMessage()
                        : "Dados inválidos.";

        return resposta(
            HttpStatus.BAD_REQUEST,
            "Dados enviados não passam nas regras de validação.",
            mensagem
        );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseError> tratarErroGenerico(Exception ex) {
        ex.printStackTrace();
        return resposta(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "Erro interno no servidor.",
                "Ocorreu uma falha inesperada no backend."
        );
    }

    private ResponseEntity<ResponseError> resposta(
            HttpStatus status,
            String titulo,
            String detalhes) {

        return ResponseEntity.status(status)
                .body(new ResponseError(
                        titulo,
                        detalhes,
                        LocalDateTime.now()
                ));
    }
}