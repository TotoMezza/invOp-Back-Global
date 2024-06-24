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

    public ProveedorArticuloServiceImpl(BaseRepository<ProveedorArticulo, Long> baseRepository) {
        super(baseRepository);
    }

    @Override
    @Transactional
    public List<ProveedorArticulo> findProveedoresByArticulo(Long articulo_id) {
            return proveedorArticuloRepository.findProveedoresByArticulo(articulo_id);
    }

    @Override
    @Transactional
    public List<ProveedorArticulo> findArticulosByProveedor(Long proveedor_id) {
            List<ProveedorArticulo> buscarArticulos = proveedorArticuloRepository.findArticulosByProveedor(proveedor_id);
            return buscarArticulos;
    }

    @Override
    @Transactional
    public ProveedorArticulo findProveedorArticuloByArticuloAndProveedor(Long proveedor_id, Long articulo_id){
        return proveedorArticuloRepository.findProveedorArticuloByProveedorAndArticulo(proveedor_id,articulo_id);
    }

}
