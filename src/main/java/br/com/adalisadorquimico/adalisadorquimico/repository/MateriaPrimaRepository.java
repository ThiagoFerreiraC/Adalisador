package br.com.adalisadorquimico.adalisadorquimico.repository;

import br.com.adalisadorquimico.adalisadorquimico.domain.MateriaPrima;
import org.springframework.data.repository.CrudRepository;

public interface MateriaPrimaRepository extends CrudRepository<MateriaPrima, Long> {

    boolean existsByDescricao(String descricao);
}
