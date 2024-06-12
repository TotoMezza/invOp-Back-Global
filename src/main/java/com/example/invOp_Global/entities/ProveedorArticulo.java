package com.example.invOp_Global.entities;

import jakarta.persistence.*;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;


@Entity
@Table(name = "proveedor_Articulo")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ProveedorArticulo extends EntidadBase{

    @NotNull
    @Column(name = "tiempo_demora")
    private int tiempoDemora;

    @NotNull
    @Column(name = "costo_almacenamiento")
    private Double costoAlmacenamiento;

    public Double getCostoAlmacenamiento() {
        return costoAlmacenamiento;
    }

    public int getTiempoDemora() {
        return tiempoDemora;
    }

    @NotNull
    @Column(name = "precio_articulo")
    private Double precioArticulo; //seria lo mismo que precio de compra

    public Double getPrecioArticulo() {
        return precioArticulo;
    }

    @NotNull
    @Column(name = "costo_pedido")
    private Double costoPedidoArticulo;


    public Double getCostoPedidoArticulo() {
        return costoPedidoArticulo;
    }

    @NotNull
    @ManyToOne
    @JoinColumn(name = "articulo_id")
    private Articulo articulo;


}