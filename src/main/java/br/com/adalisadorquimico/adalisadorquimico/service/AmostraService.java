package br.com.adalisadorquimico.adalisadorquimico.service;

import br.com.adalisadorquimico.adalisadorquimico.domain.Amostra;

import java.util.List;

public interface AmostraService {
    List<Amostra> findAll();
    Amostra findById(Long id);
    void delete(Long id);
    Amostra update(Long id, Amostra amostra);
}
