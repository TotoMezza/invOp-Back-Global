package com.example.invOp_Global.service;

import com.example.invOp_Global.entities.DetalleOrdenCompra;
import com.example.invOp_Global.repository.BaseRepository;
import com.example.invOp_Global.repository.DetalleOCRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DetalleOCServiceImpl extends BaseServiceImpl<DetalleOrdenCompra,Long> implements  DetalleOCService{

    @Autowired
    private DetalleOCRepository detalleOCRepository;

    public DetalleOCServiceImpl(BaseRepository<DetalleOrdenCompra, Long> baseRepository) {
        super(baseRepository);
    }
    @Override
    @Transactional
    public List<DetalleOrdenCompra> findDetalleOCByArticulo(Long articulo_id){
        List<DetalleOrdenCompra> buscarDetalle = detalleOCRepository.findDetalleOCByArticulo(articulo_id);
        return buscarDetalle;
    }

    @Override
    public List<DetalleOrdenCompra> findDetallesOC(Long ordenCompraId){
        return detalleOCRepository.findDetallesOC(ordenCompraId);
    }




}
