package com.example.invOp_Global.service;

import com.example.invOp_Global.entities.DetalleOrdenCompra;
import jakarta.transaction.Transactional;

import java.util.List;

public interface DetalleOCService extends BaseService<DetalleOrdenCompra,Long>{
    @Transactional
    List<DetalleOrdenCompra> findDetalleOCByArticulo(Long articulo_id);

    List<DetalleOrdenCompra> findDetallesOC(Long ordenCompraId);
}
