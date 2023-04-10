package br.com.adalisadorquimico.adalisadorquimico.dto;

import br.com.adalisadorquimico.adalisadorquimico.domain.Amostra;
import br.com.adalisadorquimico.adalisadorquimico.domain.MateriaPrima;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class ProjetoDTO {
    @NotBlank(message = "Nome do Projeto não pode ser vazio")
    private String nomeProjeto;
    @NotBlank(message = "Pesquisador não pode ser vazio")
    private String pesquisador;
    @Size(min = 1, message = "Necessário inserir uma matéria-prima")
    private Set<Long> materiasPrimas;

    //TODO resultados devem ser colocados em um outro DTO
}
