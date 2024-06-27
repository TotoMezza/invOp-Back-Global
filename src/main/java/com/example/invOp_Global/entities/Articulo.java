package com.example.invOp_Global.entities;

import com.example.invOp_Global.enums.ModeloInventario;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;

import static com.example.invOp_Global.enums.ModeloInventario.LOTE_FIJO;

@Entity
@Table(name = "articulos")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})

public class Articulo extends EntidadBase {

    @NotNull
    @Column(name = "nombre_articulo")
    private String nombre;

    @NotNull
    @Column(name = "precio_articulo")
    private Double precio;

    @Column(name = "costo_almacenamiento")
    private Double costoAlmacenamiento;

    @Column(name = "costo_pedido")
    private Double costoPedido;

    @NotNull
    @Column(name = "stock_actual")
    private int stockActual;

    @Column(name = "stock_seguridad")
    private int stockSeguridad;

    @Column(name = "cgi")
    private Double cgi;

    @Column(name = "demanda_anual")
    private Integer demandaAnual;

    @NotNull
    @Column( name = "modelo_inventario")
    @Enumerated(EnumType.STRING)
    private ModeloInventario modeloInventario;

    @Column(name = "lote_optimo")
    private Integer loteOptimo;

    @Column(name = "punto_pedido")
    private Integer puntoPedido;

    @Column(name = "cantidad_maxima")
    private Integer cantidadMaxima;

    @Column(name = "cantidad_a_pedir")
    private Integer cantidadAPedir;


    @Column(name = "tiempo_revision")
    private Double tiempoRevision;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "proveedor_predeterminado")
    private Proveedor proveedorPred;


}