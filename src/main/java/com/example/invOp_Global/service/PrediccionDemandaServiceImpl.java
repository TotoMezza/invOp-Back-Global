package com.example.invOp_Global.service;

import com.example.invOp_Global.entities.PrediccionDemanda;
import com.example.invOp_Global.repository.BaseRepository;
import com.example.invOp_Global.repository.PrediccionDemandaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PrediccionDemandaServiceImpl extends BaseServiceImpl<PrediccionDemanda,Long> implements PrediccionDemandaService{


    @Autowired
    private DemandaService demandaService;

    @Autowired
    private PrediccionDemandaRepository prediccionDemandaRepository;

    @Autowired
    private ArticuloService articuloService;


    public PrediccionDemandaServiceImpl(BaseRepository<PrediccionDemanda, Long> baseRepository, PrediccionDemandaRepository prediccionDemandaRepository) {
        super(baseRepository);
    }
}
