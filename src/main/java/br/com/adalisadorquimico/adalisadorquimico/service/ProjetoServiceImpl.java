package br.com.adalisadorquimico.adalisadorquimico.service;

import br.com.adalisadorquimico.adalisadorquimico.dto.ResponseDTO;
import br.com.adalisadorquimico.adalisadorquimico.exceptions.ResultadoDRXInvalidoException;
import br.com.adalisadorquimico.adalisadorquimico.repository.AmostraRepository;
import br.com.adalisadorquimico.adalisadorquimico.domain.Amostra;
import br.com.adalisadorquimico.adalisadorquimico.domain.Projeto;
import br.com.adalisadorquimico.adalisadorquimico.exceptions.ProjetoNomeConflictException;
import br.com.adalisadorquimico.adalisadorquimico.exceptions.ProjetoNotFoundException;
import br.com.adalisadorquimico.adalisadorquimico.repository.ProjetoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProjetoServiceImpl implements ProjetoService {
    private final ProjetoRepository repository;
    private final AmostraRepository amostraRepository;
    private final RestTemplate restTemplate;

    @Override
    public List<Projeto> findAll() {
        return (List<Projeto>) repository.findAll();
    }

    @Override
    public Projeto save(Projeto projeto) {
        if (repository.existsByNomeProjeto(projeto.getNomeProjeto())) {
            throw new ProjetoNomeConflictException();
        }
        createAmostra(projeto);
        return repository.save(projeto);
    }

    @Override
    public Projeto findById(Long id) {
        return repository.findById(id)
                .orElseThrow(ProjetoNotFoundException::new);
    }

    @Override
    public void delete(Long id) {
        repository.delete(findById(id));
    }

    @Override
    public Projeto update(Long id, Projeto projeto) {
        if (repository.existsById(id)) {
            projeto.setId(id);
            return repository.save(projeto);
        }
        throw new ProjetoNotFoundException();
    }

    private void createAmostra(Projeto projeto) {
        projeto.getMateriasPrimas().forEach(
                item -> {
                    Amostra amostra = Amostra.builder()
                            .materiaPrima(item)
                            .projeto(projeto)
                            .build();
                    amostraRepository.save(amostra);
                });
    }

    @Override
    public String findResultById(Long id) {
        if (repository.existsById(id)) {
            Projeto projeto = findById(id);
            return getResults(projeto);
        }
        throw new ProjetoNotFoundException();
    }

    @Override
    public String findResultByProjectName(String nomeProjeto) {
        if (repository.existsByNomeProjeto(nomeProjeto)) {
            Projeto projeto = repository.findByNomeProjeto(nomeProjeto);
            return getResults(projeto);
        }
        throw new ProjetoNotFoundException();
    }

    @Override
    public String findByPesquisador(String pesquisador) {
        return findAll().stream()
                .filter(item -> item.getPesquisador().equals(pesquisador))
                .map(item -> {
                    return "Projeto "
                            + item.getNomeProjeto()
                            + "\n"
                            + getResults(item)
                            + "\n";
                })
                .reduce((a, b) -> a + b)
                .get();
    }

    private String getResults(Projeto projeto) {
        Set<Amostra> amostras = projeto.getAmostras();
        List<String> propriedades = getProperties(amostras);
        return generateResult(propriedades, amostras);
    }

    private List<String> getProperties(Set<Amostra> amostras) {
        return amostras.stream()
                .map(Amostra::getResultadosDRX)
                .flatMap(Set::stream)
                .map(item -> {
                    try {
                        String url = "https://pubchem.ncbi.nlm.nih.gov/rest/pug/compound/name/"
                                + item
                                + "/property/MolecularWeight,MolecularFormula/JSON";
                        ResponseDTO responseDTO = restTemplate.getForObject(url, ResponseDTO.class);
                        return responseDTO.getPropertyTable().getProperties();
                    } catch (HttpStatusCodeException ex) {
                        throw new ResultadoDRXInvalidoException();
                    }
                })
                .flatMap(List::stream)
                .map(item -> {
                    return "Fórmula Molecular "
                            + item.getMolecularFormula()
                            + ", MM "
                            + item.getMolecularWeight()
                            + " g/mol";
                })
                .toList();
    }

    private String generateResult(List<String> propriedades, Set<Amostra> amostras) {
        String composicao = "";
        List<String> nomesDosCompostos = amostras.stream()
                .toList()
                .stream()
                .map(Amostra::getResultadosDRX)
                .flatMap(Set::stream)
                .toList();

        for (int i = 0; i < amostras.size(); i++) {
            composicao += "\t"
                    + amostras.stream()
                    .toList()
                    .get(i)
                    .getMateriaPrima()
                    .getDescricao()
                    + ": Nome "
                    + nomesDosCompostos.get(i)
                    + ", "
                    + propriedades.get(i)
                    + "\n";
        }
        return composicao;
    }
}
