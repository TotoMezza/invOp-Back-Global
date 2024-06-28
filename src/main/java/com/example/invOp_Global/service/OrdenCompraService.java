package com.example.invOp_Global.service;

import com.example.invOp_Global.dtos.CrearOCDto;
import com.example.invOp_Global.dtos.ModificarOCDto;
import com.example.invOp_Global.entities.Articulo;
import com.example.invOp_Global.entities.OrdenCompra;
import jakarta.transaction.Transactional;

import java.util.List;

public interface OrdenCompraService extends BaseService<OrdenCompra,Long>{

    public List<OrdenCompra> findOrdenCompraByEstado(String filtroEstado) throws Exception;

    @Transactional
    List<OrdenCompra> findOrdenCompraByEstadoAndArticulo(String estado, Long articulo_id);

    OrdenCompra nuevaOC(CrearOCDto crearOCDto);

    @Transactional
    OrdenCompra crearOrdenCompra(Articulo articulo);

    @Transactional
    OrdenCompra crearOrdenCompraIF(Articulo articulo);

    void modificarOC(ModificarOCDto modificarOCDto) throws Exception;

    @Transactional
    void ordenEnCurso(Long ordenCompraId) throws Exception;

    @Transactional
    void ordenFinalizada(Long ordenCompraId) throws Exception;

    @Transactional
    void cancelarOrden(Long ordenCompraId) throws Exception;
}
