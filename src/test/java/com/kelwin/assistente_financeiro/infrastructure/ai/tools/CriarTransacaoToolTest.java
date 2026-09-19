package com.kelwin.assistente_financeiro.infrastructure.ai.tools;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.kelwin.assistente_financeiro.application.service.TransacaoService;
import com.kelwin.assistente_financeiro.domain.model.TipoTransacao;
import com.kelwin.assistente_financeiro.presentation.dto.CriarTransacaoInput;
import com.kelwin.assistente_financeiro.presentation.dto.TransacaoRequest;
import com.kelwin.assistente_financeiro.presentation.dto.TransacaoResponse;

@ExtendWith(MockitoExtension.class)
class CriarTransacaoToolTest {

    @Mock
    private TransacaoService service;

    @InjectMocks
    private CriarTransacaoTool tool;

    @Test
    void deveCriarTransacaoComDataInformada() {
        LocalDateTime data = LocalDateTime.of(2026, 9, 19, 12, 30);

        CriarTransacaoInput input = criarInput(data);
        TransacaoResponse resposta = criarResposta(data);

        when(service.criarTransacao(any(TransacaoRequest.class)))
                .thenReturn(resposta);

        TransacaoResponse resultado = tool.criarTransacao(input);

        assertEquals(resposta, resultado);

        ArgumentCaptor<TransacaoRequest> captor =
                ArgumentCaptor.forClass(TransacaoRequest.class);

        verify(service).criarTransacao(captor.capture());

        TransacaoRequest request = captor.getValue();

        assertEquals("Almoço", request.descricao());
        assertEquals(new BigDecimal("25.00"), request.valor());
        assertEquals(TipoTransacao.DESPESA, request.tipo());
        assertEquals(data, request.data());
    }

    @Test
    void deveUsarDataAtualQuandoDataNaoForInformada() {
        CriarTransacaoInput input = criarInput(null);
        TransacaoResponse resposta = criarResposta(LocalDateTime.now());

        when(service.criarTransacao(any(TransacaoRequest.class)))
                .thenReturn(resposta);

        tool.criarTransacao(input);

        ArgumentCaptor<TransacaoRequest> captor =
                ArgumentCaptor.forClass(TransacaoRequest.class);

        verify(service).criarTransacao(captor.capture());

        TransacaoRequest request = captor.getValue();

        assertEquals("Almoço", request.descricao());
        assertEquals(new BigDecimal("25.00"), request.valor());
        assertEquals(TipoTransacao.DESPESA, request.tipo());
        assertNotNull(request.data());
    }

    private CriarTransacaoInput criarInput(LocalDateTime data) {
        return new CriarTransacaoInput(
                "Almoço",
                new BigDecimal("25.00"),
                TipoTransacao.DESPESA,
                data
        );
    }

    private TransacaoResponse criarResposta(LocalDateTime data) {
        return new TransacaoResponse(
                1L,
                "Almoço",
                new BigDecimal("25.00"),
                TipoTransacao.DESPESA,
                data
        );
    }
}