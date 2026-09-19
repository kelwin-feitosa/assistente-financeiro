package com.kelwin.assistente_financeiro.application.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.kelwin.assistente_financeiro.domain.model.TipoTransacao;
import com.kelwin.assistente_financeiro.domain.model.Transacao;
import com.kelwin.assistente_financeiro.domain.repository.TransacaoRepository;
import com.kelwin.assistente_financeiro.presentation.dto.TransacaoRequest;
import com.kelwin.assistente_financeiro.presentation.dto.TransacaoResponse;
import com.kelwin.assistente_financeiro.presentation.exception.TransacaoNaoEncontradaException;

@ExtendWith(MockitoExtension.class)
class TransacaoServiceTest {

    @Mock
    private TransacaoRepository repository;

    @InjectMocks
    private TransacaoService service;

    @Test
    void deveBuscarTransacaoPorId() {
        Transacao transacao = criarTransacao();

        when(repository.findById(1L))
                .thenReturn(Optional.of(transacao));

        TransacaoResponse response = service.buscarTransacao(1L);

        assertEquals(1L, response.id());
        assertEquals("Almoço", response.descricao());
        assertEquals(new BigDecimal("25.00"), response.valor());
        assertEquals(TipoTransacao.DESPESA, response.tipo());
        assertEquals(transacao.getData(), response.data());

        verify(repository).findById(1L);
    }

    @Test
    void deveLancarExcecaoQuandoTransacaoNaoExistir() {
        when(repository.findById(1L))
                .thenReturn(Optional.empty());

        assertThrows(
                TransacaoNaoEncontradaException.class,
                () -> service.buscarTransacao(1L)
        );

        verify(repository).findById(1L);
    }

    @Test
    void deveListarTransacoes() {
        Transacao transacao = criarTransacao();

        when(repository.findAll())
                .thenReturn(List.of(transacao));

        List<TransacaoResponse> response = service.listarTransacoes();

        assertEquals(1, response.size());
        assertEquals("Almoço", response.get(0).descricao());
        assertEquals(TipoTransacao.DESPESA, response.get(0).tipo());

        verify(repository).findAll();
    }

    @Test
    void deveListarTransacoesPorTipo() {
        Transacao transacao = criarTransacao();

        when(repository.findAllByTipo(TipoTransacao.DESPESA))
                .thenReturn(List.of(transacao));

        List<TransacaoResponse> response =
                service.listarTransacoesPorTipo(TipoTransacao.DESPESA);

        assertEquals(1, response.size());
        assertEquals(TipoTransacao.DESPESA, response.get(0).tipo());
        assertEquals(new BigDecimal("25.00"), response.get(0).valor());

        verify(repository).findAllByTipo(TipoTransacao.DESPESA);
    }

    @Test
    void deveCriarTransacao() {
        Transacao transacao = criarTransacao();
        TransacaoRequest request = criarRequest();

        when(repository.save(any(Transacao.class)))
                .thenReturn(transacao);

        TransacaoResponse response = service.criarTransacao(request);

        assertEquals(1L, response.id());
        assertEquals("Almoço", response.descricao());
        assertEquals(new BigDecimal("25.00"), response.valor());
        assertEquals(TipoTransacao.DESPESA, response.tipo());
        assertEquals(transacao.getData(), response.data());

        verify(repository).save(any(Transacao.class));
    }

    @Test
    void deveDeletarTransacao() {
        Transacao transacao = criarTransacao();

        when(repository.findById(1L))
                .thenReturn(Optional.of(transacao));

        service.deletarTransacao(1L);

        verify(repository).findById(1L);
        verify(repository).delete(transacao);
    }

    private Transacao criarTransacao() {
        return Transacao.builder()
                .id(1L)
                .descricao("Almoço")
                .valor(new BigDecimal("25.00"))
                .tipo(TipoTransacao.DESPESA)
                .data(LocalDateTime.of(2026, 9, 19, 12, 30))
                .build();
    }

    private TransacaoRequest criarRequest() {
        return new TransacaoRequest(
                "Almoço",
                new BigDecimal("25.00"),
                TipoTransacao.DESPESA,
                LocalDateTime.of(2026, 9, 19, 12, 30)
        );
    }
}