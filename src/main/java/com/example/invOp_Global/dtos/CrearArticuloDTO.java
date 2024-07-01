package com.example.invOp_Global.dtos;

import com.example.invOp_Global.entities.Proveedor;
import com.example.invOp_Global.enums.MetodoPrediccion;
import com.example.invOp_Global.enums.ModeloInventario;
import jakarta.persistence.*;
import lombok.Data;
import org.antlr.v4.runtime.misc.NotNull;

@Data
public class CrearArticuloDTO {

    private String nombre;

    private int stockActual;

    private ModeloInventario modeloInventario;

    private Double tiempoRevision;

    private Long idProveedorPred;

    private Double tiempoDemora;

    private Double costoAlmacenamiento;

    private Double costoPedido;

    private Double precioArticuloProveedor;

}
