package com.example.invOp_Global.service;

import com.example.invOp_Global.entities.OrdenCompra;
import jakarta.transaction.Transactional;

import java.util.List;

public interface OrdenCompraService extends BaseService<OrdenCompra,Long>{
    public List<OrdenCompra> findOrdenCompraByEstado(String filtroEstado) throws Exception;
}
