package com.example.invOp_Global.service;

import com.example.invOp_Global.entities.Proveedor;
import com.example.invOp_Global.repository.ProveedorRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class ProveedorServiceImpl extends BaseServiceImpl<Proveedor, Long> implements ProveedorService {

    @Autowired
    private ProveedorRepository proveedorRepository;
    public ProveedorServiceImpl(ProveedorRepository proveedorRepository){
        super(proveedorRepository);
        this.proveedorRepository = proveedorRepository;
    }
}
