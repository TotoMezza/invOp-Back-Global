package com.example.invOp_Global.service;

import com.example.invOp_Global.entities.VentaDetalle;

import java.util.List;

public interface VentaDetalleService extends BaseService<VentaDetalle,Long>{
    List<VentaDetalle> findDetallesVenta(Long ventaId);
}
