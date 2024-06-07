package com.example.invOp_Global.entities;

import jakarta.persistence.*;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "venta")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Venta extends EntidadBase {

    @NotNull
    private Double totalVenta;

    @NotNull
    private Date fechaVenta;

    private boolean calculoTotal; //flag para que solo se ejecute una vez el cálculo del total

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_demanda")
    private Demanda demanda;

    @NotNull
    @OneToMany
    @Builder.Default
    private List<VentaDetalle> ventaDetalles = new ArrayList<>();

    public void AgregarDetalleVenta(VentaDetalle vd) {
        ventaDetalles.add(vd);
    }

    public void CalcularTotalVenta() {

        if (calculoTotal) {
            throw new IllegalStateException("El método para calcular el total de la venta ya fue ejecutado, su resultado fue: $" + this.totalVenta);
        }
        this.totalVenta = 0.0;
        for (VentaDetalle ventaDetalle : ventaDetalles) {
            totalVenta += ventaDetalle.getSubtotal();
        }
        calculoTotal = true; //marco como true para que cuando se vuelva ejecutar pase a else y no se quede en un bucle
        System.out.println("El total de la venta es: $"+ totalVenta);
    }
}