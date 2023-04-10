package br.com.adalisadorquimico.adalisadorquimico.controller;

import br.com.adalisadorquimico.adalisadorquimico.service.AmostraService;
import br.com.adalisadorquimico.adalisadorquimico.domain.Amostra;
import br.com.adalisadorquimico.adalisadorquimico.domain.MateriaPrima;
import br.com.adalisadorquimico.adalisadorquimico.domain.Projeto;
import br.com.adalisadorquimico.adalisadorquimico.dto.AmostraSaveDTO;
import br.com.adalisadorquimico.adalisadorquimico.service.ProjetoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("amostra")
@RequiredArgsConstructor
public class AmostraController {
    private final AmostraService amostraService;
    private final ProjetoService projetoService;

    @GetMapping
    public List<Amostra> findAll() {
        return amostraService.findAll();
    }

    @GetMapping("{id}")
    public Amostra findById(@PathVariable Long id) {
        return amostraService.findById(id);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        amostraService.delete(id);
    }

    @PutMapping({"{id}"})
    public Amostra update(@PathVariable Long id, @RequestBody AmostraSaveDTO dto) {
        Projeto projeto = projetoService.findById(dto.getProjeto());
        Amostra amostra = Amostra.builder()
                .resultadosDRX(dto.getResultadosDRX())
                .materiaPrima(MateriaPrima.builder().id(dto.getMateriaPrima()).build())
                .projeto(projeto)
                .build();
        return amostraService.update(id, amostra);
    }
}
