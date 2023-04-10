package br.com.adalisadorquimico.adalisadorquimico.controller;

import br.com.adalisadorquimico.adalisadorquimico.domain.MateriaPrima;
import br.com.adalisadorquimico.adalisadorquimico.dto.MateriaPrimaDTO;
import br.com.adalisadorquimico.adalisadorquimico.service.MateriaPrimaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("materia-prima")
@RequiredArgsConstructor
public class MateriaPrimaController {
    private final MateriaPrimaService service;

    @GetMapping
    public List<MateriaPrima> findAll() {
        return service.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MateriaPrima save(@Valid @RequestBody MateriaPrimaDTO dto) {
        MateriaPrima materiaPrima = MateriaPrima.builder()
                .fracao(dto.getFracao())
                .fornecedor(dto.getFornecedor())
                .descricao(dto.getDescricao())
                .lote(dto.getLote())
                .build();
        return service.save(materiaPrima);
    }

    @GetMapping("{id}")
    public MateriaPrima findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    @PutMapping({"{id}"})
    public MateriaPrima update(@PathVariable Long id, @RequestBody MateriaPrimaDTO dto) {
        MateriaPrima materiaPrima = MateriaPrima.builder()
                .fracao(dto.getFracao())
                .fornecedor(dto.getFornecedor())
                .descricao(dto.getDescricao())
                .lote(dto.getLote())
                .build();
        //TODO usar mapper ou criar função específica, repetição do save
        return service.update(id, materiaPrima);
    }
}
