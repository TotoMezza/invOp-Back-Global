package com.example.invOp_Global.entities;

import com.example.invOp_Global.enums.MetodoPrediccion;
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
public class ErrorMedido extends EntidadBase{

    @Column(name = "porcentaje")
    private Double porcentajeError;

    @Column(name = "error_total")
    private Double errorTotal;

    @Column(name = "valor_demanda_real")
    private Integer demandaReal;

    @Column(name = "valor_prediccion_demanda")
    private Integer prediccionDemanda;

    @ManyToOne()
    @JoinColumn(name = "id_articulo")
    private Articulo articulo;

    @Column(name = "fecha_desde")
    private LocalDate fechaDesde;

    @Column(name = "fecha_hasta")
    private LocalDate fechaHasta;

    @Column(name = "nombre_metodo")
    @Enumerated(EnumType.STRING)
    private MetodoPrediccion metodoUsado;


}
