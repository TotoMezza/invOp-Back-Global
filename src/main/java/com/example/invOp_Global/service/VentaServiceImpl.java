package com.example.invOp_Global.service;

import com.example.invOp_Global.dtos.DetalleVentaDto;
import com.example.invOp_Global.entities.Articulo;
import com.example.invOp_Global.entities.Venta;
import com.example.invOp_Global.entities.VentaDetalle;
import com.example.invOp_Global.repository.ArticuloRepository;
import com.example.invOp_Global.repository.BaseRepository;
import com.example.invOp_Global.repository.VentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class VentaServiceImpl extends BaseServiceImpl<Venta, Long> implements VentaService {

    @Autowired
    private VentaRepository ventaRepository;
    @Autowired
    private ArticuloRepository articuloRepository;
    @Autowired
    private ArticuloService articuloService;


    public VentaServiceImpl(BaseRepository<Venta, Long> baseRepository, VentaRepository ventaRepository, ArticuloService articuloService){
        super(baseRepository);
        this.ventaRepository = ventaRepository;
        this.articuloRepository = articuloRepository;
        this.articuloService = articuloService;
    }
    @Override
    public List<Venta> findVentasByFechas(LocalDate fechaDesde, LocalDate fechaHasta) throws Exception {
        try {
            List<Venta> buscarVentas = ventaRepository.findVentasByFechas(fechaDesde, fechaHasta);
            return buscarVentas;
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Venta nuevaVenta(List<DetalleVentaDto> detalleVentaDto) throws Exception{
        Venta nuevaVenta = new Venta();
        nuevaVenta.setFechaVenta(LocalDate.now());
        for (DetalleVentaDto detalleVenta : detalleVentaDto){
                Articulo articulo = articuloRepository.findById(detalleVenta.getArticulo_id()).orElseThrow();
                if(articulo.getStockActual() <= detalleVenta.getCantidad()){
                    throw new Exception("El stock del articulo" + articulo.getNombre() + "no es suficiente para realizar la venta");
                }
            }
        for (DetalleVentaDto detalleVenta : detalleVentaDto){
            Articulo articulo = articuloRepository.findById(detalleVenta.getArticulo_id()).orElseThrow();
            VentaDetalle ventaDetalle = new VentaDetalle();
            ventaDetalle.setArticulo(articulo);
            ventaDetalle.setCantidadVenta(detalleVenta.getCantidad());
            ventaDetalle.setSubtotal(detalleVenta.getCantidad()*articulo.getPrecio());

            nuevaVenta.AgregarDetalleVenta(ventaDetalle);
            nuevaVenta.setTotalVenta(nuevaVenta.getTotalVenta()+ventaDetalle.getSubtotal());

            articuloService.disminuirStock(articulo, detalleVenta.getCantidad());

            if (articulo.getStockActual() <= articulo.getStockSeguridad()){

            }

        }
            return  nuevaVenta;

    }



}
