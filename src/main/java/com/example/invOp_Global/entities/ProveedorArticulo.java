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
    @Column(name = "costo_almacenamiento")
    private Double costoAlmacenamiento;

    @NotNull
    @Column(name = "costo_pedido")
    private Double costoPedido;

    @Column(name = "precio_articulo_proveedor")
    private Double precioArticuloProveedor;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_articulo")
    private Articulo articulo;

    @NotNull
    @ManyToOne()
    @JoinColumn(name = "id_proveedor")
    private Proveedor proveedor;

}