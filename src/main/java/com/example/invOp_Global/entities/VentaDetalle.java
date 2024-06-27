package com.example.invOp_Global.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class VentaDetalle extends EntidadBase {

    @NotNull
    @Column(name = "cantidad_venta")
    private int cantidadVenta;


    @Column(name= "subtotal")
    private Double subtotal;

    @NotNull
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_Articulo")
    private Articulo articulo;

    public VentaDetalle(Articulo articulo, Integer cantidadVenta) {
        this.articulo = articulo;
        this.cantidadVenta = cantidadVenta;
    }

}