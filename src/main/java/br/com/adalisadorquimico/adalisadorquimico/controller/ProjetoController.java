package br.com.adalisadorquimico.adalisadorquimico.controller;

import br.com.adalisadorquimico.adalisadorquimico.domain.MateriaPrima;
import br.com.adalisadorquimico.adalisadorquimico.domain.Projeto;
import br.com.adalisadorquimico.adalisadorquimico.dto.ProjetoDTO;
import br.com.adalisadorquimico.adalisadorquimico.service.ProjetoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("projeto")
@RequiredArgsConstructor
public class ProjetoController {
    private final ProjetoService service;

    @GetMapping
    public List<Projeto> findAll() {
        return service.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Projeto save(@Valid @RequestBody ProjetoDTO dto) {
        Projeto projeto = Projeto.builder()
                .nomeProjeto(dto.getNomeProjeto())
                .pesquisador(dto.getPesquisador())
                .materiasPrimas(dto.getMateriasPrimas().
                        stream().map(item -> MateriaPrima.builder().id(item).build())
                        .collect(Collectors.toSet()))
                .build();
        return service.save(projeto);
    }

    @GetMapping("{id}")
    public Projeto findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    @PutMapping({"{id}"})
    public Projeto update(@PathVariable Long id, @RequestBody ProjetoDTO dto) {
        Projeto projeto = Projeto.builder()
                .nomeProjeto(dto.getNomeProjeto())
                .pesquisador(dto.getPesquisador())
                .materiasPrimas(dto.getMateriasPrimas().
                        stream().map(item -> MateriaPrima.builder().id(item).build())
                        .collect(Collectors.toSet()))
                .build();
        return service.update(id, projeto);
    }

    @GetMapping("/resultado/{id}")
    public String findResultById(@PathVariable Long id) {
        return service.findResultById(id);
    }

    @GetMapping("/resultado/nome/{nomeProjeto}")
    public String findResultByProjectName(@PathVariable String nomeProjeto) {
        return service.findResultByProjectName(nomeProjeto);
    }

    @GetMapping("/resultado/pesquisador/{pesquisador}")
    public String findByPesquisador(@PathVariable String pesquisador) {
        return service.findByPesquisador(pesquisador);
    }
}
