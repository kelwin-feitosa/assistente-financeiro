package com.kelwin.assistente_financeiro.presentation.exception;

public class TransacaoNaoEncontradaException extends RuntimeException{
    public TransacaoNaoEncontradaException(String mensagem) {
        super(mensagem);
    }
}
