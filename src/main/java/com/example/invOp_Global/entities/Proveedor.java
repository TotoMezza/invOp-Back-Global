package com.example.invOp_Global.entities;

import jakarta.persistence.*;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "proveedores")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder

public class Proveedor extends EntidadBase{

    @NotNull
    @Column(name = "nombre_proveedor")
    private String nombreProveedor;

}