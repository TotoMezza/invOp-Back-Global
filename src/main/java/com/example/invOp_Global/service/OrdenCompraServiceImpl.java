package com.example.invOp_Global.service;

import com.example.invOp_Global.entities.OrdenCompra;
import com.example.invOp_Global.repository.BaseRepository;
import com.example.invOp_Global.repository.OrdenCompraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrdenCompraServiceImpl extends BaseServiceImpl<OrdenCompra,Long> implements OrdenCompraService{

    @Autowired
    private OrdenCompraRepository ordenCompraRepository;

    public OrdenCompraServiceImpl(BaseRepository<OrdenCompra, Long> baseRepository) {
        super(baseRepository);
    }
}

