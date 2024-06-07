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
    private int cantidadVenta;

    @NotNull
    private Double subtotal;


    @NotNull
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_Articulo")
    private Articulo articulo;


    public Double getSubtotal() {
        return subtotal;
    }


    public void setArticulo(Articulo articulo) {
        this.articulo = articulo;
    }

    public void setCantidadVenta(int cantidadVenta) {
        this.cantidadVenta = cantidadVenta;
    }

    public void setSubtotal(int cantidadVenta, Articulo articulo) {
        this.subtotal = cantidadVenta*articulo.getPrecio();
    }
}