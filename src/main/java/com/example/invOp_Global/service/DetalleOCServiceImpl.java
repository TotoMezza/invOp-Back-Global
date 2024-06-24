package com.example.invOp_Global.service;

import com.example.invOp_Global.entities.DetalleOrdenCompra;
import com.example.invOp_Global.repository.BaseRepository;
import com.example.invOp_Global.repository.DetalleOCRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DetalleOCServiceImpl extends BaseServiceImpl<DetalleOrdenCompra,Long> implements  DetalleOCService{

    @Autowired
    private DetalleOCRepository detalleOCRepository;

    public DetalleOCServiceImpl(BaseRepository<DetalleOrdenCompra, Long> baseRepository) {
        super(baseRepository);
    }


}
