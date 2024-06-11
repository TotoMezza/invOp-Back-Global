package com.example.invOp_Global.service;

import com.example.invOp_Global.entities.Venta;
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
    private ArticuloService articuloService;

    public VentaServiceImpl(BaseRepository<Venta, Long> baseRepository, VentaRepository ventaRepository, ArticuloService articuloService){
        super(baseRepository);
        this.ventaRepository = ventaRepository;
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
}
