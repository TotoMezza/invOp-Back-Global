package com.example.invOp_Global.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "ventas")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Venta extends EntidadBase {

    @Column(name = "total_venta")
    private Double totalVenta;

    @Column(name = "fecha_venta")
    private LocalDate fechaVenta;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_venta")
    @Builder.Default
    private List<VentaDetalle> ventaDetalles = new ArrayList<>();

    public void AgregarDetalleVenta(VentaDetalle vd) {
        ventaDetalles.add(vd);
    }


}