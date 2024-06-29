package com.example.invOp_Global.service;

import com.example.invOp_Global.dtos.CrearArticuloDTO;
import com.example.invOp_Global.entities.Articulo;
import jakarta.transaction.Transactional;

import java.util.List;

public interface ArticuloService extends BaseService<Articulo,Long>{

    Articulo crearArticulo(CrearArticuloDTO crearArticuloDTO) throws Exception;

    public Articulo findById(Long id);

    public  void disminuirStock(Articulo articulo, Integer cantVendida);

    boolean darBajaArticulo(Long idArticulo) throws Exception;

    List<Articulo> listadoFaltantes();

    List<Articulo> listadoAReponer();

    int calcularStockSeguridad(Long articuloId);

    Integer demandaAnual(Long articuloId) throws Exception;

    Integer calculoPuntoPedido(Long articuloId) throws Exception;

    Integer calculoLoteOptimo(Long articuloId) throws Exception;

    Double calculoCGI(Long articuloId) throws Exception;

    void calculosLoteFijo(Long articuloId) throws Exception;

    Integer calculoCantidadMax(Long articuloId) throws Exception;

    Integer calculoCantAPedir(Long articuloId) throws Exception;

    void calculosIntervaloFijo(Long articuloId) throws Exception;

    void modificarModeloInventario(Long articuloId) throws Exception;

    void modificarValoresProveedor(Long proveedorId, Long articuloId) throws Exception;

    void calcularTodo(Long articuloId) throws Exception;

}
