package com.example.invOp_Global.service;

import com.example.invOp_Global.entities.Articulo;
import jakarta.transaction.Transactional;

import java.util.List;

public interface ArticuloService extends BaseService<Articulo,Long>{
    public Articulo findById(Long id);

    public  void disminuirStock(Articulo articulo, Integer cantVendida);

    @Transactional
    void darBajaArticulo(Long idArticulo) throws Exception;

    List<Articulo> listadoFaltantes();

    int calcularStockSeguridad(Long articuloId);

    void calculosLoteFijo(Long articuloId) throws Exception;

    Integer calculoCantidadMax(Long articuloId) throws Exception;

    Integer calculoCantAPedir(Long articuloId) throws Exception;

    void calculosIntervaloFijo(Long articuloId) throws Exception;

    void modificarIntervaloFijo(Long articuloId) throws Exception;

    void modificarLoteFijo(Long articuloId) throws Exception;
}
