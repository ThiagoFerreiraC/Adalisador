package br.com.adalisadorquimico.adalisadorquimico.service;

import br.com.adalisadorquimico.adalisadorquimico.domain.Amostra;
import br.com.adalisadorquimico.adalisadorquimico.repository.AmostraRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AmostraServiceImpl implements AmostraService {
    private final AmostraRepository repository;

    @Override
    public List<Amostra> findAll() {
        return (List<Amostra>) repository.findAll();
    }

    @Override
    public Amostra findById(Long id) {
        //TODO fazer exceção de notFound
        return repository.findById(id).get();
    }

    @Override
    public void delete(Long id) {
        repository.delete(findById(id));
    }

    @Override
    public Amostra update(Long id, Amostra amostra) {
        findById(id);
        amostra.setId(id);
        //TODO fazer exceção de notFound
        return repository.save(amostra);
    }
}
