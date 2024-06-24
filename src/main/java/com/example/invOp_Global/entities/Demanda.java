package com.example.invOp_Global.entities;

import jakarta.persistence.*;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "demanda")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Demanda extends EntidadBase{

    @NotNull
    @Column(name = "fecha_desde")
    private LocalDate fechaDesde;

    @NotNull
    @Column(name = "fecha_hasta")
    private LocalDate fechaHasta;

    @Column(name = "total_demanda")
    private Integer totalDemanda;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_demanda")
    @Builder.Default
    private List<Venta> listaVentas=new ArrayList<>();

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_articulo")
    private Articulo articulo;

}