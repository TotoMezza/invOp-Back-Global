package com.example.invOp_Global.dtos;

import com.example.invOp_Global.enums.MetodoPrediccion;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class ParametrosPrediccionDTO {
    private Long articuloId;
    private LocalDate fechaDesde;
    private LocalDate fechaHasta;
    private List<Double> coeficientes;
    private Integer mesPrediccion;
    private Integer anioPrediccion;
    private MetodoPrediccion metodoPrediccion;
    private Double alfa;
    private int cantidadPeriodos;

}
