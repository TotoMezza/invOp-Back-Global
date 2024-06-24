package com.example.invOp_Global.service;

import com.example.invOp_Global.entities.Demanda;

import java.time.LocalDate;
import java.util.Date;

public interface DemandaService extends  BaseService<Demanda,Long>{

   public Demanda nuevaDemandaHistorica(LocalDate fechaDesde, LocalDate fechaHasta, Long idArticulo);

   public Integer calcularDemandaHistorica(LocalDate fechaDesde, LocalDate fechaHasta, Long idArticulo);
}
