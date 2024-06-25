package com.example.invOp_Global.dtos;

import lombok.Data;

@Data
public class ReponerDto {
    private Long articuloId;
    private String nombre;
    private Integer stockActual;
    private Integer puntoPedido;
}
