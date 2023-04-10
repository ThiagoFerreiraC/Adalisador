package br.com.adalisadorquimico.adalisadorquimico.repository;

import br.com.adalisadorquimico.adalisadorquimico.domain.Projeto;
import org.springframework.data.repository.CrudRepository;

public interface ProjetoRepository extends CrudRepository<Projeto, Long> {
    boolean existsByNomeProjeto(String nomeProjeto);
    Projeto findByNomeProjeto(String nomeProjeto);
}
