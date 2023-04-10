package br.com.adalisadorquimico.adalisadorquimico.service;

import br.com.adalisadorquimico.adalisadorquimico.domain.Projeto;

import java.util.List;

public interface ProjetoService {
    List<Projeto> findAll();

    Projeto save(Projeto projeto);

    Projeto findById(Long id);

    void delete(Long id);

    Projeto update(Long id, Projeto projeto);

    String findResultById(Long id);

    String findResultByProjectName(String nomeProjeto);

    String findByPesquisador(String pesquisador);
}
