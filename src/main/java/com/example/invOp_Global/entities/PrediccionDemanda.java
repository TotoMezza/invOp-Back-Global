package com.example.invOp_Global.entities;

import com.example.invOp_Global.enums.MetodoPrediccion;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;

import java.time.LocalDate;

@Entity
@Table(name = "prediccion_demanda")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class PrediccionDemanda extends EntidadBase{

    @Column(name = "fecha_prediccion")
    private LocalDate fechaPrediccion;

    @Column(name= "valor_prediccion")
    private Integer valorPrediccion;

    @ManyToOne
    @JoinColumn(name="id_demanda")
    private Demanda demanda;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="id_articulo")
    private Articulo articulo;

    @NotNull
    @Column(name = "metodo_prediccion")
    @Enumerated(EnumType.STRING)
    private MetodoPrediccion metodoPrediccion;
}
