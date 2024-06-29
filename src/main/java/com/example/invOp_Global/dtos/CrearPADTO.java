package com.example.invOp_Global.dtos;

import com.example.invOp_Global.entities.Articulo;
import com.example.invOp_Global.entities.Proveedor;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
public class CrearPADTO {

    private Double tiempoDemora;

    private Double costoAlmacenamiento;

    private Double costoPedido;

    private Double precioArticuloProveedor;

    private Long idArticulo;

    private Long idProveedor;

}
