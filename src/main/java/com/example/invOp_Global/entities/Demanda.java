package com.example.invOp_Global.entities;

import jakarta.persistence.*;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "demanda")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Demanda extends EntidadBase{

    @Column(name = "total_Demanda")
    private double totalDemanda;

    @OneToMany
    @JoinColumn(name = "id_Demanda")
    @Builder.Default
    private List<Venta> listaVentas=new ArrayList<>();
     public void agregarVenta(Venta v){
         listaVentas.add(v);
     }
    private boolean calculoTotal=false; //flag para que solo se ejecute una vez el cálculo del total
     public void CalcularTotalDemanda(){
         if (calculoTotal) {
             System.out.println("El método para calcular el total de la demanda ya fue ejecutado, su resultado fue: $" + this.totalDemanda);
             return; // Salir del método si ya se calculó
         }
         this.totalDemanda = 0.0;
         for (Venta venta : listaVentas) {
             totalDemanda += venta.getTotalVenta();
         }
         calculoTotal = true; //marco como true para que cuando se vuelva ejecutar pase a else y no se quede en un bucle
         System.out.println("El total de la demanda es: $"+ totalDemanda);
     }


    public double getTotalDemanda() {
        return totalDemanda;
    }

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_articulo")
    private Articulo articulo;

    @NotNull
    @Column(name = "fecha_desde")
    private Date fechaDesde;

    @NotNull
    @Column(name = "fecha_hasta")
    private Date fechaHasta;

}