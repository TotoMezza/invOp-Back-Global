package com.example.invOp_Global.entities;

import com.example.invOp_Global.enums.ModeloInventario;
import jakarta.persistence.*;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.ArrayList;
import java.util.List;
import org.antlr.v4.runtime.misc.NotNull;

@Entity
@Table(name = "articulos")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder

public class Articulo extends EntidadBase {

    @NotNull
    @Column(name = "nombre-Articulo")
    private String nombre;

    @NotNull
    @Column(name = "precio-Articulo")
    private Double precio;

    @NotNull
    @Column( name = "modelo_Inventario")
    private ModeloInventario modeloInventario;

    @NotNull
    @Column(name = "stock-Actual")
    private int stockActual;

    @NotNull
    @Column(name = "lote_Optimo")
    private Integer loteOptimo;

    @NotNull
    @Column(name = "stock_Seguridad")
    private Integer stockSeguridad;

    @NotNull
    @Column(name = "costo_almacenamiento")
    private Double costoAlmacenamiento;

    @NotNull
    @Column(name = "cgi")
    private Double cgi;

    @Column(name = "punto_pedido")
    private Integer puntoPedido;
}