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
    private Double tiempoDemora;

    @NotNull
    @Column(name = "precio_articulo")
    private Double precioArticulo;

    @NotNull
    @Column(name = "costo_pedido")
    private Double costoPedidoArticulo;

    @NotNull
    @Column(name = "costo_almacenamiento")
    private Double costoAlmacenamiento;

    @Column(name = "punto_pedido")
    private Integer puntoPedido;

    @Column(name = "cgi")
    private Double cgi;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "articulo_id")
    private Articulo articulo;


}