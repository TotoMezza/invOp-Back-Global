package com.example.invOp_Global.entities;

import jakarta.persistence.*;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.Date;

@Entity
@Table(name = "demanda")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Demanda extends EntidadBase{

    @Column(name = "total_Demanda")
    private int totalDemanda;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_articulo")
    private Articulo articulo;

    @NotNull
    @Column(name = "fecha_desde")
    private Date fechaDesde;

    @NotNull
    @Column(name = "fecha_hasta")
    private Date fechaHasta;

}