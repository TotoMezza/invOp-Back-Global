package com.example.invOp_Global.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "error")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder

public class MedicionError extends EntidadBase {

    @Column(name = "sumatoria_error")
    private Double porcentajeError;

    @Column(name = "valor_demanda_real")
    private Integer valorDemandaReal;

    @Column(name = "valor_prediccion_demanda")
    private Integer valorPrediccionDemanda;

    @ManyToOne()
    @JoinColumn(name = "id_articulo")
    private Articulo articulo;

    @Column(name = "fecha_desde")
    private LocalDate fechaDesde;

    @Column(name = "fecha_hasta")
    private LocalDate fechaHasta;

}
