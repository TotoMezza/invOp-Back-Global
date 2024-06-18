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


    @Column(name = "stock-Actual")
    private int stockActual;


    @Column(name = "lote_Optimo")
    private Integer loteOptimo;


    @Column(name = "stock_Seguridad")
    private int stockSeguridad;


    @Column(name = "cgi")
    private Double cgi;

    @Column(name = "punto_pedido")
    private Integer puntoPedido;

    public void calcularValores(Demanda d, ProveedorArticulo pa){
        double auxCp=pa.getCostoPedidoArticulo();
        double auxD=d.getTotalDemanda();


        stockActual=50; //idem anterior
        if (modeloInventario == LOTE_FIJO){
            int auxTD=pa.getTiempoDemora();
            stockSeguridad= (int) Math.round(1.65*Math.sqrt(auxTD));
            puntoPedido= stockSeguridad+(int) Math.round(auxTD*auxD);
            periodoPedido =null;
            loteOptimo = (int) Math.round(Math.sqrt(2 * auxD * (auxCp / pa.getCostoAlmacenamiento())));
        } else{
            puntoPedido= null;
            loteOptimo=null;
            stockSeguridad=(int) Math.round(1.65*Math.sqrt(periodoPedido+pa.getTiempoDemora()));
            //periodoPedido es con un controller supongo

        }

        cgi=pa.getPrecioArticulo()*d.getTotalDemanda()+pa.getCostoAlmacenamiento()*loteOptimo/2+pa.getCostoPedidoArticulo()*d.getTotalDemanda()/loteOptimo;
    }

    public void setPeriodoPedido(Integer periodoPedido) {
        this.periodoPedido = periodoPedido;
    }


}