package com.example.invOp_Global.service;

import com.example.invOp_Global.entities.Articulo;
import com.example.invOp_Global.repository.ArticuloRepository;
import com.example.invOp_Global.repository.BaseRepository;
import com.example.invOp_Global.repository.VentaRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class ArticuloServiceImpl extends BaseServiceImpl<Articulo,Long> implements ArticuloService {

    @Autowired
    private ArticuloRepository articuloRepository;

    @Autowired
    private VentaRepository ventaRepository;

    public ArticuloServiceImpl(BaseRepository<Articulo, Long> baseRepository, ArticuloRepository articuloRepository) {
        super(baseRepository);
        this.articuloRepository = articuloRepository;
    }

}
