package com.kelwin.assistente_financeiro.infrastructure.ai.tools;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.kelwin.assistente_financeiro.application.service.TransacaoService;
import com.kelwin.assistente_financeiro.domain.model.TipoTransacao;
import com.kelwin.assistente_financeiro.presentation.dto.TransacaoResponse;

@ExtendWith(MockitoExtension.class)
class ConsultarTransacoesToolTest {

    @Mock
    private TransacaoService service;

    @InjectMocks
    private ConsultarTransacoesTool tool;

    @Test
    void deveConsultarTransacoesPorTipo() {
        TipoTransacao tipo = TipoTransacao.DESPESA;
        List<TransacaoResponse> transacoes = List.of(criarResposta(tipo));

        when(service.listarTransacoesPorTipo(tipo))
                .thenReturn(transacoes);

        List<TransacaoResponse> resultado =
                tool.consultarTransacoesPorTipo(tipo);

        assertEquals(transacoes, resultado);

        verify(service).listarTransacoesPorTipo(tipo);
    }

    private TransacaoResponse criarResposta(TipoTransacao tipo) {
        return new TransacaoResponse(
                1L,
                "Almoço",
                new BigDecimal("25.00"),
                tipo,
                LocalDateTime.of(2026, 9, 19, 12, 30)
        );
    }
}