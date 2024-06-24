package com.example.invOp_Global.service;

import com.example.invOp_Global.entities.ProveedorArticulo;
import jakarta.transaction.Transactional;

import java.util.List;

public interface ProveedorArticuloService extends BaseService<ProveedorArticulo,Long>{
    @Transactional
    public List<ProveedorArticulo> findProveedoresByArticulo(Long filtroArticulo) throws Exception;

    @Transactional
    public List<ProveedorArticulo> findArticulosByProveedor(Long filtroProveedor) throws Exception;

    @Transactional
    public ProveedorArticulo findProveedorArticuloByArticuloAndProveedor(Long proveedor_id, Long articulo_id);
}
