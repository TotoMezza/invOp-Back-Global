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
    public List<ProveedorArticulo> findProveedoresByArticulo(Long filtroArticulo) throws Exception{
        try {
            return proveedorArticuloRepository.findProveedoresByArticulo(filtroArticulo);
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public List<ProveedorArticulo> findArticulosByProveedor(Long filtroProveedor) throws Exception {
        try {
            List<ProveedorArticulo> buscarArticulos = proveedorArticuloRepository.findArticulosByProveedor(filtroProveedor);
            return buscarArticulos;
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

}
