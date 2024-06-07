package com.example.invOp_Global.entities;

import jakarta.persistence.*;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.Date;

@Entity
@Table(name = "prediccion")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Prediccion extends EntidadBase{
    @NotNull
    private Date fechaPrediccion;

    @NotNull
    private int periodoPrediccion;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_Articulo")
    private Articulo articulo;

}