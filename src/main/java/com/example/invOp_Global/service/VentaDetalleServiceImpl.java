package com.example.invOp_Global.service;

import com.example.invOp_Global.entities.VentaDetalle;
import com.example.invOp_Global.repository.VentaDetalleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VentaDetalleServiceImpl extends BaseServiceImpl<VentaDetalle,Long> implements  VentaDetalleService{

    @Autowired
    private VentaDetalleRepository ventaDetalleRepository;

    public VentaDetalleServiceImpl(VentaDetalleRepository ventaDetalleRepository){
        super(ventaDetalleRepository);
        this.ventaDetalleRepository = ventaDetalleRepository;
    }

    @Override
    public List<VentaDetalle> findDetallesVenta(Long ventaId){
        return  ventaDetalleRepository.findDetallesVenta(ventaId);
    }

}
