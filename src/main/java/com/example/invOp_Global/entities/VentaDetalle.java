package com.example.invOp_Global.entities;

import jakarta.persistence.*;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;

@Entity
@Table(name = "venta-detalles")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class VentaDetalle extends EntidadBase {

    @NotNull
    @Column(name = "cantidad_venta")
    private int cantidadVenta;

    @NotNull
    @Column(name= "subtotal")
    private Double subtotal;

    @NotNull
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_Articulo")
    private Articulo articulo;

    public VentaDetalle(Articulo articulo, Integer cantidadVenta) {
        this.articulo = articulo;
        this.cantidadVenta = cantidadVenta;
    }

}