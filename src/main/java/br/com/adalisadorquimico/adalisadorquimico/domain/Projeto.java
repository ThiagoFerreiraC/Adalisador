package br.com.adalisadorquimico.adalisadorquimico.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Projeto {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String nomeProjeto;
    private String pesquisador;
    @ManyToMany
    @JoinTable(
            name = "projeto_materia_prima",
            joinColumns = @JoinColumn(name = "projeto_id"),
            inverseJoinColumns = @JoinColumn(name = "materia_prima_id")
    )
    @JsonIgnoreProperties("projeto")
    private Set<MateriaPrima> materiasPrimas;

    @OneToMany(mappedBy = "projeto",
            cascade = CascadeType.ALL)
    @JsonBackReference
    private Set<Amostra> amostras;
}
