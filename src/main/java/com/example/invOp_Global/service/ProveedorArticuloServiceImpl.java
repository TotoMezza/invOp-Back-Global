package com.example.invOp_Global.service;

import com.example.invOp_Global.entities.ProveedorArticulo;
import com.example.invOp_Global.repository.BaseRepository;
import com.example.invOp_Global.repository.ProveedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProveedorArticuloServiceImpl extends BaseServiceImpl<ProveedorArticulo,Long> implements ProveedorArticuloService {

    @Autowired
    private ProveedorRepository proveedorRepository;


    public ProveedorArticuloServiceImpl(BaseRepository<ProveedorArticulo, Long> baseRepository) {
        super(baseRepository);
    }
}
