package com.kelwin.assistente_financeiro.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kelwin.assistente_financeiro.domain.model.Transacao;

public interface TransacaoRepository extends JpaRepository<Transacao, Long>{

}
