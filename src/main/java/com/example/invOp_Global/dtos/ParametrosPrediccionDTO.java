package com.example.invOp_Global.dtos;

import com.example.invOp_Global.entities.EntidadBase;
import com.example.invOp_Global.enums.MetodoPrediccion;
import lombok.*;

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

    private Integer cantidadPeriodosAPredecir;
    private Integer cantidadPeriodosAUsar;
    private Integer cantidadDemandaAnual;

    private double error;
    private double porcentajeDeError;
    private double prediccion;

}
