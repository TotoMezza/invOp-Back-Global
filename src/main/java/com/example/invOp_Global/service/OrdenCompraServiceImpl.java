package com.example.invOp_Global.service;

import com.example.invOp_Global.entities.OrdenCompra;
import com.example.invOp_Global.repository.BaseRepository;
import com.example.invOp_Global.repository.OrdenCompraRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrdenCompraServiceImpl extends BaseServiceImpl<OrdenCompra,Long> implements OrdenCompraService{

    @Autowired
    private OrdenCompraRepository ordenCompraRepository;

    public OrdenCompraServiceImpl(BaseRepository<OrdenCompra, Long> baseRepository) {
        super(baseRepository);
        this.ordenCompraRepository = ordenCompraRepository;
    }
    @Override
    @Transactional
    public List<OrdenCompra> findOrdenCompraByEstado(String filtroEstado) throws Exception{
        try {
            List<OrdenCompra> buscarOrdenes = ordenCompraRepository.findOrdenCompraByEstado(filtroEstado);
            return buscarOrdenes;
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }
}

