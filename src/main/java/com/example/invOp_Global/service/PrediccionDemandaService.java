package com.example.invOp_Global.service;

import com.example.invOp_Global.entities.PrediccionDemanda;

import java.util.List;

public interface PrediccionDemandaService extends BaseService<PrediccionDemanda,Long>{

    List<PrediccionDemanda> buscarPrediccionDemanda(Long filroArticulo) throws  Exception;

}

