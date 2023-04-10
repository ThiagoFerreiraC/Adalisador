package br.com.adalisadorquimico.adalisadorquimico.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.validator.internal.util.stereotypes.Lazy;

import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MateriaPrima {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String fracao;
    private String descricao;
    private String lote;
    private String fornecedor;
    @ManyToMany(mappedBy = "materiasPrimas", fetch = FetchType.LAZY)
    @JsonIgnoreProperties("materiasPrimas")
    private List<Projeto> projeto;
}
