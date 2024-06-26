package com.example.invOp_Global.service;

import com.example.invOp_Global.entities.Demanda;
import com.example.invOp_Global.entities.Venta;
import com.example.invOp_Global.entities.VentaDetalle;
import com.example.invOp_Global.repository.ArticuloRepository;
import com.example.invOp_Global.repository.DemandaRepository;
import com.example.invOp_Global.repository.VentaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class DemandaServiceImpl extends BaseServiceImpl<Demanda,Long> implements  DemandaService{

    @Autowired
    private DemandaRepository demandaRepository;

    @Autowired
    private ArticuloRepository articuloRepository;

    @Autowired
    private VentaRepository ventaRepository;

    public DemandaServiceImpl(DemandaRepository demandaRepository, VentaRepository ventaRepository, ArticuloRepository articuloRepository
    ) {
        super(demandaRepository);
        this.articuloRepository = articuloRepository;
        this.demandaRepository = demandaRepository;
        this.ventaRepository = ventaRepository;
    }

    @Override
    public Demanda nuevaDemanda(LocalDate fechaDesde, LocalDate fechaHasta, Long idArticulo){
        int totalDemanda = calcularDemanda(fechaDesde,fechaHasta,idArticulo);
        return crearDemanda(fechaDesde,fechaHasta,idArticulo,totalDemanda);

    }

    @Override
    public Integer calcularDemanda(LocalDate fechaDesde, LocalDate fechaHasta, Long idArticulo){
        List<Venta> ventas = ventaRepository.findVentasByFechas(fechaDesde,fechaHasta);
        int totalDemanda = 0;
        for (Venta venta: ventas){
            for (VentaDetalle ventaDetalle : venta.getVentaDetalles()){
                if (ventaDetalle.getArticulo().getId().equals(idArticulo)){
                    totalDemanda += ventaDetalle.getCantidadVenta();
                }
            }
        }
        return totalDemanda;
    }

    @Override
    public Demanda crearDemanda(LocalDate fechaDesde, LocalDate fechaHasta, Long idArticulo, Integer totalDemanda){
        Demanda demanda = new Demanda();
        demanda.setFechaDesde(fechaDesde);
        demanda.setFechaHasta(fechaHasta);
        demanda.setTotalDemanda(totalDemanda);
        demanda.setArticulo(articuloRepository.findById(idArticulo).orElseThrow(() -> new EntityNotFoundException("Articulo no encontrado")));
        demandaRepository.save(demanda);
        return demanda;

    }
}

