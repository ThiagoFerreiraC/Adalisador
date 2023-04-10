package br.com.adalisadorquimico.adalisadorquimico.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Amostra {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "materia_prima_id")
    @JsonIgnoreProperties
    private MateriaPrima materiaPrima;
    @ElementCollection
    @CollectionTable(name="resultados_DRX",
            joinColumns=@JoinColumn(name="amostra_id"))
    @Column(name="resultado")
    private Set<String> resultadosDRX;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonManagedReference
    private Projeto projeto;
}
