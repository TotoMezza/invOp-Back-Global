package com.example.invOp_Global.service;

import com.example.invOp_Global.dtos.ParametrosPrediccionDTO;
import com.example.invOp_Global.entities.PrediccionDemanda;

import java.time.LocalDate;
import java.util.List;

public interface PrediccionDemandaService extends BaseService<PrediccionDemanda,Long>{

    public List<PrediccionDemanda> findPrediccionDemandaArticulo(Long articuloId) throws  Exception;

    PrediccionDemanda findPrediccionDemandaArticuloByFechas(Long articuloId, int anio, int mes) throws  Exception;

    Integer prediccionPMPonderado(ParametrosPrediccionDTO parametrosPrediccionDTO) throws Exception;

    Integer calculoPMPSuavizado(ParametrosPrediccionDTO parametrosPrediccionDTO) throws Exception;
}

