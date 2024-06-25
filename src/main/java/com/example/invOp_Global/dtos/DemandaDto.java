package com.example.invOp_Global.dtos;

import lombok.Data;

import java.time.LocalDate;

@Data
public class DemandaDto {
    private Long articuloId;
    private LocalDate fechaDesde;
    private LocalDate fechaHasta;
}
