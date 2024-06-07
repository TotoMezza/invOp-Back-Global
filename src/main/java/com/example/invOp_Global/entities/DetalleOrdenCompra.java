package com.example.invOp_Global.entities;

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

public class DetalleOrdenCompra extends EntidadBase{
    @NotNull
    private  Integer cantidadOCD;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_Articulo")
    private Articulo articulo;
}