package com.example.invOp_Global.entities;

import jakarta.persistence.*;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "prediccion_demanda")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class PrediccionDemanda extends EntidadBase{

    @Column(name = "fecha_prediccion")
    private LocalDate fechaPrediccion;

    @NotNull
    @Column(name= "valor_prediccion")
    private Integer valorPrediccion;

    @ManyToOne
    @NotNull
    @JoinColumn(name="id_prediccion")
    private Demanda demanda;

    @ManyToOne
    @JoinColumn(name ="id_articulo")
    private Articulo articulo;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name ="predicciones_demandas",
            joinColumns = @JoinColumn(name = "id_prediccion_demanda"),
            inverseJoinColumns = @JoinColumn(name = "id_demanda")
    )
    private List<Demanda> prediccionesDemanda;

    @Column(name = "error_medido")
    private Double errorMedido;
}
