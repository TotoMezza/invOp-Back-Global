package com.example.invOp_Global.entities;

import com.example.invOp_Global.enums.EstadoOrdenCompra;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class OrdenCompra extends EntidadBase{

    @NotNull
    @Column(name = "fecha_oc")
    private LocalDate fechaOrdenCompra;

    @Column(name = "total_oc")
    private  double totalOrdenCompra;


    @Column(name = "estado_oc")
    @Enumerated(EnumType.STRING)
    private EstadoOrdenCompra estadoOrdenCompra;

    @NotNull
    @ManyToOne()
    @JoinColumn(name = "id_proveedor")
    private Proveedor proveedor;

    @NotNull
    @OneToMany(cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
    @Builder.Default
    @JoinColumn(name ="id_OrdenCompra")
    private List<DetalleOrdenCompra> DetallesOC = new ArrayList<>();

    public void agregarDetalleOrdenCompra(DetalleOrdenCompra ordenCompraDetalle){
        DetallesOC.add(ordenCompraDetalle);
    }


}