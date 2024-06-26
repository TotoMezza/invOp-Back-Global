package com.example.invOp_Global.service;

import com.example.invOp_Global.entities.ProveedorArticulo;
import com.example.invOp_Global.repository.BaseRepository;
import com.example.invOp_Global.repository.ProveedorArticuloRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProveedorArticuloServiceImpl extends BaseServiceImpl<ProveedorArticulo,Long> implements ProveedorArticuloService {

    @Autowired
    private ProveedorArticuloRepository proveedorArticuloRepository;

    public ProveedorArticuloServiceImpl(ProveedorArticuloRepository proveedorArticuloRepository){
        super(proveedorArticuloRepository);
        this.proveedorArticuloRepository = proveedorArticuloRepository;
    }

    @Override
    @Transactional
    public List<ProveedorArticulo> findProveedoresByArticulo(Long articuloId) throws Exception{
        try {
            return proveedorArticuloRepository.findProveedoresByArticulo(articuloId);
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    public List<ProveedorArticulo> findArticulosByProveedor(Long proveedor_id) {
            List<ProveedorArticulo> buscarArticulos = proveedorArticuloRepository.findArticulosByProveedor(proveedor_id);
            return buscarArticulos;
    }


    public ProveedorArticulo findProveedorArticuloByArticuloAndProveedor(Long proveedorId, Long articuloId){
        return proveedorArticuloRepository.findProveedorArticuloByProveedorAndArticulo(proveedorId,articuloId);
    }

}
