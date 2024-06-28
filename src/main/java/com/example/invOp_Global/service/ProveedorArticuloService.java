package com.example.invOp_Global.service;

import com.example.invOp_Global.entities.ProveedorArticulo;

import java.util.List;

public interface ProveedorArticuloService extends BaseService<ProveedorArticulo,Long>{
    public List<ProveedorArticulo> findProveedoresByArticulo(Long articuloId) throws Exception;
    public List<ProveedorArticulo> findArticulosByProveedor(Long proveedor_id);
    public ProveedorArticulo findProveedorArticuloByArticuloAndProveedor(Long proveedorId, Long articuloId);
}
