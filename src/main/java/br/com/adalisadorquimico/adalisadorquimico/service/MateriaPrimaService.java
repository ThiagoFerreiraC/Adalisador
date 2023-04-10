package br.com.adalisadorquimico.adalisadorquimico.service;

import br.com.adalisadorquimico.adalisadorquimico.domain.MateriaPrima;

import java.util.List;

public interface MateriaPrimaService {
    List<MateriaPrima> findAll();
    MateriaPrima save(MateriaPrima materiaPrima);
    MateriaPrima findById(Long id);
    void delete(Long id);
    MateriaPrima update(Long id, MateriaPrima materiaPrima);
}
