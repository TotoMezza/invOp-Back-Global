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

    private boolean calculoTotal=false; //flag para que solo se ejecute una vez el cálculo del total

    @NotNull
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @Builder.Default
    @JoinColumn(name = "id_Venta")
    private List<VentaDetalle> ventaDetalles = new ArrayList<>();

    public void AgregarDetalleVenta(VentaDetalle vd) {
        ventaDetalles.add(vd);
    }

    public void CalcularTotalVenta() {

        if (calculoTotal) {
            System.out.println("El método para calcular el total de la venta ya fue ejecutado, su resultado fue: $" + this.totalVenta);
            return; // Salir del método si ya se calculó
        }

        this.totalVenta = 0.0;
        for (VentaDetalle ventaDetalle : ventaDetalles) {
            totalVenta += ventaDetalle.getSubtotal();
        }
        calculoTotal = true; // marcar como false para indicar que ya se calculó una vez
        System.out.println("El total de la venta es: $" + totalVenta+""+calculoTotal);
    }


    public Double getTotalVenta() {
        return totalVenta;
    }
}