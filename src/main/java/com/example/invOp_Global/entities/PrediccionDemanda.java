package com.example.invOp_Global.entities;

import jakarta.persistence.*;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;

@Entity
@Table(name = "prediccion_demanda")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class PrediccionDemanda extends EntidadBase{

    @NotNull
    @Column(name= "valor_prediccion")
    private Integer valorPrediccion;

    @ManyToOne
    @NotNull
    @JoinColumn(name="id_prediccion")
    private Demanda demanda;

}
