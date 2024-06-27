package com.example.invOp_Global.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Demanda extends EntidadBase{

    @NotNull
    @Column(name = "fecha_desde")
    private LocalDate fechaDesde;

    @NotNull
    @Column(name = "fecha_hasta")
    private LocalDate fechaHasta;

    @Column(name = "total_demanda")
    private Integer totalDemanda;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_articulo")
    private Articulo articulo;

}