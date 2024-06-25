package com.example.invOp_Global.entities;

import com.example.invOp_Global.enums.MetodoPrediccion;
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
public class PrediccionDemanda extends EntidadBase{

    @Column(name = "fecha_prediccion")
    private LocalDate fechaPrediccion;

    @Column(name= "valor_prediccion")
    private Integer valorPrediccion;

    @ManyToOne
    @JoinColumn(name="id_demanda")
    private Demanda demanda;

    @ManyToOne
    @JoinColumn(name ="id_articulo")
    private Articulo articulo;

    @Column(name = "error_medido")
    private Double errorMedido;

    @NotNull
    @Column(name = "metodo_prediccion")
    private MetodoPrediccion metodoPrediccion;
}
