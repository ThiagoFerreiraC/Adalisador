package br.com.adalisadorquimico.adalisadorquimico.service;

import br.com.adalisadorquimico.adalisadorquimico.domain.MateriaPrima;
import br.com.adalisadorquimico.adalisadorquimico.exceptions.MateriaPrimaDescricaoConflictException;
import br.com.adalisadorquimico.adalisadorquimico.exceptions.MateriaPrimaNotFoundException;
import br.com.adalisadorquimico.adalisadorquimico.repository.MateriaPrimaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MateriaPrimaServiceImpl implements MateriaPrimaService{

    private final MateriaPrimaRepository repository;
    @Override
    public List<MateriaPrima> findAll() {
        return (List<MateriaPrima>) repository.findAll();
    }

    @Override
    public MateriaPrima save(MateriaPrima materiaPrima) {
        if (repository.existsByDescricao(materiaPrima.getDescricao())) {
            throw new MateriaPrimaDescricaoConflictException();
        }
        return repository.save(materiaPrima);
    }

    @Override
    public MateriaPrima findById(Long id) {
        return repository.findById(id).orElseThrow(MateriaPrimaNotFoundException::new);
    }

    @Override
    public void delete(Long id) {
        repository.delete(findById(id));
    }

    @Override
    public MateriaPrima update(Long id, MateriaPrima materiaPrima) {
        if (repository.existsById(id)) {
            materiaPrima.setId(id);
            return repository.save(materiaPrima);
        }
        throw new MateriaPrimaNotFoundException();
    }
}
