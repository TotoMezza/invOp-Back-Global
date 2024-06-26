package com.example.invOp_Global.service;

import com.example.invOp_Global.entities.Articulo;
import com.example.invOp_Global.entities.DetalleOrdenCompra;
import com.example.invOp_Global.entities.OrdenCompra;
import com.example.invOp_Global.enums.EstadoOrdenCompra;
import com.example.invOp_Global.repository.ArticuloRepository;
import com.example.invOp_Global.repository.BaseRepository;
import com.example.invOp_Global.repository.OrdenCompraRepository;
import com.example.invOp_Global.repository.ProveedorRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class OrdenCompraServiceImpl extends BaseServiceImpl<OrdenCompra,Long> implements OrdenCompraService{

    @Autowired
    private OrdenCompraRepository ordenCompraRepository;
    @Autowired
    private ArticuloRepository articuloRepository;
    @Autowired
    private ProveedorRepository proveedorRepository;

    public OrdenCompraServiceImpl(OrdenCompraRepository ordenCompraRepository, ProveedorRepository proveedorRepository){
        super(ordenCompraRepository);
        this.ordenCompraRepository = ordenCompraRepository;
        this.proveedorRepository = proveedorRepository;
        this.articuloRepository = articuloRepository;

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

    @Override
    @Transactional
   public OrdenCompra crearOrdenCompra(Articulo articulo) {
       OrdenCompra ordenCompra = new OrdenCompra();
       ordenCompra.setFechaOrdenCompra(LocalDate.now());
       ordenCompra.setProveedor(articulo.getProveedorPred());

       DetalleOrdenCompra detalleOC = new DetalleOrdenCompra();
       detalleOC.setArticulo(articulo);
       detalleOC.setCantidadOCD(articulo.getLoteOptimo());
       detalleOC.setSubtotal(articulo.getPrecio() * detalleOC.getCantidadOCD());

       ordenCompra.setTotalOrdenCompra(detalleOC.getSubtotal());
       ordenCompra.agregarDetalleOrdenCompra(detalleOC);
       ordenCompra.setEstadoOrdenCompra(EstadoOrdenCompra.PENDIENTE);

       ordenCompraRepository.save(ordenCompra);
        return ordenCompra;
    }

    @Override
    @Transactional
    public void ordenEnCurso(Long ordenCompraId) throws Exception {
        OrdenCompra ordenCompra = ordenCompraRepository.findById(ordenCompraId)
                .orElseThrow(()-> new Exception("No se encontro la orden de compra"));
        if (ordenCompra.getEstadoOrdenCompra().equals(EstadoOrdenCompra.PENDIENTE)){
            ordenCompra.setEstadoOrdenCompra(EstadoOrdenCompra.EN_CURSO);
            ordenCompraRepository.save(ordenCompra);
        }else {
            throw new Exception("La orden de compra no está pendiente");
        }
    }

    @Override
    @Transactional
    public void ordenFinalizada(Long ordenCompraId) throws Exception {
        OrdenCompra ordenCompra = ordenCompraRepository.findById(ordenCompraId)
                .orElseThrow(()-> new Exception("No se encontro la orden de compra"));
        if (ordenCompra.getEstadoOrdenCompra().equals(EstadoOrdenCompra.EN_CURSO)){
            ordenCompra.setEstadoOrdenCompra(EstadoOrdenCompra.FINALIZADA);
            ordenCompraRepository.save(ordenCompra);
            for (DetalleOrdenCompra detalle : ordenCompra.getDetallesOC()){
                Articulo articulo = detalle.getArticulo();
                articulo.setStockActual(articulo.getStockActual()+detalle.getCantidadOCD());
                articuloRepository.save(articulo);
            }
        }else {
            throw new Exception("La orden de compra no está en curso");
        }
    }

    @Override
    @Transactional
    public void cancelarOrden(Long ordenCompraId) throws Exception {
        OrdenCompra ordenCompra = ordenCompraRepository.findById(ordenCompraId)
                .orElseThrow(()-> new Exception("No se encontro la orden de compra"));
        if (ordenCompra.getEstadoOrdenCompra().equals(EstadoOrdenCompra.FINALIZADA)){
            throw new Exception("La orden de compra no se puede dar de baja ya que está finalizada");
        }
        ordenCompra.setEstadoOrdenCompra(EstadoOrdenCompra.CANCELADA);
        ordenCompraRepository.save(ordenCompra);
    }

}

