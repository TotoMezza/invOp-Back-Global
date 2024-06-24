package com.example.invOp_Global.service;

import com.example.invOp_Global.entities.Articulo;
import com.example.invOp_Global.entities.DetalleOrdenCompra;
import com.example.invOp_Global.entities.OrdenCompra;
import com.example.invOp_Global.repository.BaseRepository;
import com.example.invOp_Global.repository.OrdenCompraRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Service
public class OrdenCompraServiceImpl extends BaseServiceImpl<OrdenCompra,Long> implements OrdenCompraService{

    @Autowired
    private OrdenCompraRepository ordenCompraRepository;

    public OrdenCompraServiceImpl(BaseRepository<OrdenCompra, Long> baseRepository) {
        super(baseRepository);
        this.ordenCompraRepository = ordenCompraRepository;
    }
    @Override
    @Transactional
    public List<OrdenCompra> findOrdenCompraByEstado(String estado){
            List<OrdenCompra> buscarOrden = ordenCompraRepository.findOrdenCompraByEstado(estado);
            return buscarOrden;
    }

    @Override
    @Transactional
    public List<OrdenCompra> findOrdenCompraByEstadoAndArticulo(String estado, Long articulo_id){
        List<OrdenCompra> buscarOrden = ordenCompraRepository.findOrdenCompraByEstadoAndArticulo(estado,articulo_id);
        return buscarOrden;
    }

  /*  public OrdenCompra crearOrdenCompra(Articulo articulo){
        OrdenCompra ordenCompra = new OrdenCompra();

        ordenCompra.setFechaOrdenCompra(LocalDate.now());
        ordenCompra.setProveedor(articulo.getProveedorPred());


    }*/
}

