package com.example.invOp_Global.dtos;

import lombok.Data;

@Data
public class FaltanteDto {
    private Long articuloId;
    private String nombre;
    private Integer stockActual;
    private Integer stockSeguridad;
}