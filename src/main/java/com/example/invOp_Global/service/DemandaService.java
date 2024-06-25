package com.example.invOp_Global.service;

import com.example.invOp_Global.entities.Demanda;

import java.time.LocalDate;
import java.util.Date;

public interface DemandaService extends  BaseService<Demanda,Long>{

   Demanda nuevaDemanda(LocalDate fechaDesde, LocalDate fechaHasta, Long idArticulo);

   Integer calcularDemanda(LocalDate fechaDesde, LocalDate fechaHasta, Long idArticulo);

   Demanda crearDemanda(LocalDate fechaDesde, LocalDate fechaHasta, Long idArticulo, Integer totalDemanda);
}
