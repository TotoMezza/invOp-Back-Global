package com.example.invOp_Global.service;

import com.example.invOp_Global.dtos.ParametrosPrediccionDTO;
import com.example.invOp_Global.entities.PrediccionDemanda;

import java.time.LocalDate;
import java.util.List;

public interface PrediccionDemandaService extends BaseService<PrediccionDemanda,Long>{

    public List<PrediccionDemanda> findPrediccionDemandaArticulo(Long articuloId) throws  Exception;

    PrediccionDemanda findPrediccionDemandaArticuloByFechas(Long articuloId, int anio, int mes) throws  Exception;

    Integer calculoPMPonderado(ParametrosPrediccionDTO parametrosPrediccionDTO) throws Exception;

    Integer calculoPMPSuavizado(ParametrosPrediccionDTO parametrosPrediccionDTO) throws Exception;

    Integer calculoPEstacional(ParametrosPrediccionDTO parametrosPrediccionDTO) throws Exception;

    Integer calculoRegresionLineal(ParametrosPrediccionDTO parametrosPrediccionDTO) throws Exception;

    List<PrediccionDemanda> crearPrediccion(ParametrosPrediccionDTO parametrosPrediccionDTO) throws Exception;

    List<PrediccionDemanda> crearPrediccionPredeterminada(ParametrosPrediccionDTO parametrosPrediccionDTO) throws Exception;

    void calculoError(ParametrosPrediccionDTO parametrosPrediccionDTO) throws Exception;

    void calcularError(ParametrosPrediccionDTO parametrosPrediccionDTO, int contador) throws Exception;
}

