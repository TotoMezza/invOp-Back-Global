package com.example.invOp_Global.service;

import com.example.invOp_Global.entities.ErrorMedido;
import com.example.invOp_Global.repository.BaseRepository;
import com.example.invOp_Global.repository.ErrorMedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ErrorMedidoServiceImpl extends BaseServiceImpl<ErrorMedido,Long> implements ErrorMedidoService {
    @Autowired
    private ErrorMedidoRepository errorMetodoRepository;

    @Autowired
    private ArticuloService articuloService;

    @Autowired
    private PrediccionDemandaService prediccionDemandaService;

    @Autowired
    private DemandaService demandaService;

    public ErrorMedidoServiceImpl(ErrorMedidoRepository errorMedidoRepository, ArticuloService articuloService, DemandaService demandaService, PrediccionDemandaService prediccionDemandaService) {
        super(errorMedidoRepository);
        this.errorMetodoRepository = errorMetodoRepository;
        this.articuloService = articuloService;
        this.demandaService = demandaService;
        this.prediccionDemandaService = prediccionDemandaService;
    }

}
