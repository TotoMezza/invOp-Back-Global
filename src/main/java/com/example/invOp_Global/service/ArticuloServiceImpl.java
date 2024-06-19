package com.example.invOp_Global.service;

import com.example.invOp_Global.entities.Articulo;
import com.example.invOp_Global.entities.OrdenCompra;
import com.example.invOp_Global.enums.EstadoOrdenCompra;
import com.example.invOp_Global.repository.ArticuloRepository;
import com.example.invOp_Global.repository.BaseRepository;
import com.example.invOp_Global.repository.VentaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArticuloServiceImpl extends BaseServiceImpl<Articulo,Long> implements ArticuloService {

    @Autowired
    private ArticuloRepository articuloRepository;
    @Autowired
    private OrdenCompraService ordenCompraService;
    @Autowired
    private DemandaService demandaService;
    @Autowired
    private ProveedorArticuloService proveedorArticuloService;
    @Autowired
    private ProveedorService proveedorService;
    @Autowired
    private VentaRepository ventaRepository;

    public ArticuloServiceImpl(BaseRepository<Articulo, Long> baseRepository, ArticuloRepository articuloRepository) {
        super(baseRepository);
        this.articuloRepository = articuloRepository;
        this.demandaService = demandaService;
        this.ordenCompraService = ordenCompraService;
        this.proveedorService = proveedorService;
        this.proveedorArticuloService = proveedorArticuloService;
    }

    @Override
    public Articulo findById(Long id){
        return articuloRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("El artículo no se ha encontrado"));
    }

    //Metodo Disminuir Stock en Venta
    public void disminuirStock(Articulo articulo, Integer cantVendida){
        Integer stockModificado = articulo.getStockActual() - cantVendida;
        articulo.setStockActual(stockModificado);
        articuloRepository.save(articulo);
    }

    /*Metodo Dar de Baja Artículo
    public boolean darDeBaja(Long articuloId) {
        Articulo articulo = articuloRepository.findById(articuloId).orElseThrow();
        for (OrdenCompra ordenCompra : articulo.get()) {
            if (ordenCompra.getEstadoOrdenCompra() == EstadoOrdenCompra.PENDIENTE) {
                return false; // no se puede dar de baja si hay órdenes pendientes
            }
        }
        articuloRepository.delete(articulo);
        return true; // artículo eliminado con éxito
    }
    */

}
