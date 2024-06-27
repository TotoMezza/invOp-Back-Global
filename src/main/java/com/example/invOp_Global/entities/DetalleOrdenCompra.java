package com.example.invOp_Global.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;

@Entity
@Table(name = "ordencompradetalle")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class DetalleOrdenCompra extends EntidadBase{
    @NotNull
    @Column(name = "cantidad")
    private Integer cantidadOCD;

    @ManyToOne( fetch = FetchType.LAZY)
    @JoinColumn(name = "id_Articulo")
    private Articulo articulo;

    @Column(name = "subtotal")
    private Double subtotal;
}