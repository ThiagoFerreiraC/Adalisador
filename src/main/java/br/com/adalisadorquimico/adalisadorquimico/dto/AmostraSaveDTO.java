package br.com.adalisadorquimico.adalisadorquimico.dto;

import br.com.adalisadorquimico.adalisadorquimico.domain.MateriaPrima;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AmostraSaveDTO {
    @Size(min = 1, message = "Deve haver no mínimo um resultado")
    private Set<String> resultadosDRX;
    @NotBlank(message = "Matéria-Prima não pode ser vazia")
    private Long materiaPrima;
    @NotBlank(message = "Projeto não pode ser vazio")
    private Long projeto;
}
