package br.com.adalisadorquimico.adalisadorquimico.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class MateriaPrimaDTO {
    @NotBlank(message = "Fração não pode ser vazia")
    private String fracao;
    @NotBlank(message = "Descrição não pode ser vazia")
    private String descricao;
    @NotBlank(message = "Lote não pode ser vazio")
    private String lote;
    @NotBlank(message = "Forncedor não pode ser vazio")
    private String fornecedor;
}
