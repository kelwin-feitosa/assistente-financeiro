package com.kelwin.assistente_financeiro.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kelwin.assistente_financeiro.domain.model.TipoTransacao;
import com.kelwin.assistente_financeiro.domain.model.Transacao;

public interface TransacaoRepository extends JpaRepository<Transacao, Long>{
    public List<Transacao> findAllByTipo(TipoTransacao tipo);
}
