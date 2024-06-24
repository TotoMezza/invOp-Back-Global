package com.example.invOp_Global.service;

import com.example.invOp_Global.dtos.DetalleVentaDto;
import com.example.invOp_Global.entities.Venta;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface VentaService extends BaseService<Venta,Long>{


    List<Venta> findVentasByFechas(LocalDate fechaDesde, LocalDate fechaHasta) throws Exception;

    Venta nuevaVenta(List<DetalleVentaDto> detalleVentaDto) throws Exception;
}
