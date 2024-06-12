package com.example.invOp_Global.entities;

import com.example.invOp_Global.enums.ModeloInventario;
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

public class Articulo extends EntidadBase {

    @NotNull
    @Column(name = "nombre-Articulo")
    private String nombre;

    @NotNull
    @Column(name = "precio-Articulo")
    private Double precio;

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Double getPrecio() {
        return precio;
    }

    @NotNull
    @Column( name = "modelo_Inventario")
    @Enumerated(EnumType.STRING)
    private ModeloInventario modeloInventario;

    @Column(name="periodoPedido")
    private Integer periodoPedido;

    @NotNull
    @Column(name = "stock-Actual")
    private int stockActual;

    @NotNull
    @Column(name = "lote_Optimo")
    private int loteOptimo;

    @NotNull
    @Column(name = "stock_Seguridad")
    private int stockSeguridad;

    @NotNull
    @Column(name = "cgi")
    private Double cgi;

    @Column(name = "punto_pedido")
    private Integer puntoPedido;

    public void calcularValores(Demanda d, ProveedorArticulo pa){
        double auxCp=pa.getCostoPedidoArticulo();
        double auxD=d.getTotalDemanda();

        stockSeguridad=40; //no se la formula asi q mientras ponemos este
        stockActual=50; //idem anterior
        if (modeloInventario == LOTE_FIJO){
            int auxTD=pa.getTiempoDemora();
            puntoPedido= (int) Math.round(auxTD*auxD);
            periodoPedido =null;
            loteOptimo = (int) Math.round(Math.sqrt(2 * auxD * (auxCp / pa.getCostoAlmacenamiento())));
        } else{
            puntoPedido= null;
            //Formula de lote optimo (cantidad) para tiempo fijo tiene desviación estándar
        }

        cgi=pa.getPrecioArticulo()*d.getTotalDemanda()+pa.getCostoAlmacenamiento()*loteOptimo/2+pa.getCostoPedidoArticulo()*d.getTotalDemanda()/loteOptimo;
    }

}