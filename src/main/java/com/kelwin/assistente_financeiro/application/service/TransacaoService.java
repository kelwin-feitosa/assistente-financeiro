package com.kelwin.assistente_financeiro.application.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kelwin.assistente_financeiro.domain.model.Transacao;
import com.kelwin.assistente_financeiro.domain.repository.TransacaoRepository;
import com.kelwin.assistente_financeiro.presentation.dto.TransacaoRequest;
import com.kelwin.assistente_financeiro.presentation.dto.TransacaoResponse;
import com.kelwin.assistente_financeiro.presentation.exception.TransacaoNaoEncontradaException;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor 
@Service 
public class TransacaoService {

    private final TransacaoRepository repository;

    public TransacaoResponse buscarTransacao(Long id) {
        return toResponse(buscarEntidade(id));
    }

    public List<TransacaoResponse> listarTransacoes() {
        return repository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public TransacaoResponse criarTransacao(TransacaoRequest request) {
        Transacao transacao = repository.save(toEntity(request));

        return toResponse(transacao);
    }

    public void deletarTransacao(Long id) {
        repository.delete(buscarEntidade(id));
    }

    private Transacao buscarEntidade(Long id) {
        return repository.findById(id)
            .orElseThrow(() -> new TransacaoNaoEncontradaException("Transação não encontrada!"));
    }

    private Transacao toEntity(TransacaoRequest request) {
        return Transacao.builder()
                .descricao(request.descricao())
                .valor(request.valor())
                .tipo(request.tipo())
                .data(request.data())
                .build();
    }

    private TransacaoResponse toResponse(Transacao transacao) {
        return new TransacaoResponse(
                transacao.getId(),
                transacao.getDescricao(),
                transacao.getValor(),
                transacao.getTipo(),
                transacao.getData()
        );
    }
}
