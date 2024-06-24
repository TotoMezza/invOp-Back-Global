package com.example.invOp_Global.entities;

import com.example.invOp_Global.enums.EstadoOrdenCompra;
import jakarta.persistence.*;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "ordencompra")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class OrdenCompra extends EntidadBase{

    @NotNull
    @Column(name = "fecha_oc")
    private LocalDate fechaOrdenCompra;

    @NotNull
    @Column(name = "total_oc")
    private  double totalOrdenCompra;

    @NotNull
    @Column(name = "estado_oc")
    @Enumerated(EnumType.STRING)
    private EstadoOrdenCompra estadoOrdenCompra;

    @NotNull
    @ManyToOne()
    @JoinColumn(name = "id_proveedor")
    private Proveedor proveedor;

    @NotNull
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @Builder.Default
    @JoinColumn(name ="id_OrdenCompra")
    private List<DetalleOrdenCompra> DetallesOC = new ArrayList<>();

    public void agregarDetalleOrdenCompra(DetalleOrdenCompra ordenCompraDetalle){
        DetallesOC.add(ordenCompraDetalle);
    }


}