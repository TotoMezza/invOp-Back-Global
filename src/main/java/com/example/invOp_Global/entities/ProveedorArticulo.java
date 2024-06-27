package com.example.invOp_Global.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;


@Entity
@Table(name = "proveedor_articulo")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class ProveedorArticulo extends EntidadBase{


    @Column(name = "tiempo_demora")
    private Double tiempoDemora;


    @Column(name = "costo_almacenamiento")
    private Double costoAlmacenamiento;


    @Column(name = "costo_pedido")
    private Double costoPedido;

    @Column(name = "precio_articulo_proveedor")
    private Double precioArticuloProveedor;


    @ManyToOne
    @JoinColumn(name = "id_articulo")
    private Articulo articulo;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_proveedor")
    private Proveedor proveedor;

}