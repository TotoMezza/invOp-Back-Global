package com.example.invOp_Global.service;

import com.example.invOp_Global.entities.Demanda;
import com.example.invOp_Global.repository.ArticuloRepository;
import com.example.invOp_Global.repository.BaseRepository;
import com.example.invOp_Global.repository.DemandaRepository;
import com.example.invOp_Global.repository.VentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DemandaServiceImpl extends BaseServiceImpl<Demanda,Long> implements  DemandaService{

    @Autowired
    private DemandaRepository demandaRepository;

    @Autowired
    private ArticuloRepository articuloRepository;

    @Autowired
    private VentaRepository ventaRepository;

    public DemandaServiceImpl(DemandaRepository demandaRepository, VentaRepository ventaRepository, ArticuloRepository articuloRepository
    ) {
        super(demandaRepository);
        this.articuloRepository = articuloRepository;
        this.demandaRepository = demandaRepository;
        this.ventaRepository = ventaRepository;
    }
}
