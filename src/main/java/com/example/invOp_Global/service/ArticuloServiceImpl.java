package com.example.invOp_Global.service;

import com.example.invOp_Global.dtos.CrearArticuloDTO;
import com.example.invOp_Global.entities.*;
import com.example.invOp_Global.enums.ModeloInventario;
import com.example.invOp_Global.repository.*;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ArticuloServiceImpl extends BaseServiceImpl<Articulo,Long> implements ArticuloService {

    @Autowired
    private ArticuloRepository articuloRepository;
    @Autowired
    private OrdenCompraRepository ordenCompraRepository;
    @Autowired
    private DemandaService demandaService;
    @Autowired
    private ProveedorArticuloService proveedorArticuloService;
    @Autowired
    private ProveedorArticuloRepository proveedorArticuloRepository;
    @Autowired
    private ProveedorService proveedorService;
    @Autowired
    private VentaRepository ventaRepository;
    @Autowired
    private ProveedorRepository proveedorRepository;
    @Autowired
    private DetalleOCService detalleOCService;

    @Autowired
    private DetalleOCRepository detalleOCRepository;

    public ArticuloServiceImpl(ArticuloRepository articuloRepository) {
        super(articuloRepository);
        this.articuloRepository = articuloRepository;
        this.demandaService = demandaService;
        this.ordenCompraRepository = ordenCompraRepository;
        this.proveedorService = proveedorService;
        this.proveedorArticuloRepository = proveedorArticuloRepository;
        this.proveedorArticuloService = proveedorArticuloService;
        this.proveedorRepository = proveedorRepository;
        this.detalleOCService = detalleOCService;
    }

    @Override
    public Articulo crearArticulo(CrearArticuloDTO crearArticuloDTO) throws Exception {


        Articulo articulo = new Articulo();

            articulo.setNombre(crearArticuloDTO.getNombre());
            articulo.setStockActual(crearArticuloDTO.getStockActual());
            articulo.setModeloInventario(crearArticuloDTO.getModeloInventario());
            articulo.setTiempoRevision(crearArticuloDTO.getTiempoRevision());
            articulo.setPrecio(crearArticuloDTO.getPrecio());


        Proveedor proveedor = proveedorRepository.findById(crearArticuloDTO.getIdProveedorPred()).orElseThrow();

        articulo.setProveedorPred(proveedor);

        return articuloRepository.save(articulo);
    }

    @Override
    public Articulo findById(Long id){
        return articuloRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("El artículo no se ha encontrado"));
    }

    @Override
    public void disminuirStock(Articulo articulo, Integer cantVendida){
        Integer stockModificado = articulo.getStockActual() - cantVendida;
        articulo.setStockActual(stockModificado);
        articuloRepository.save(articulo);
    }

    @Override
    @Transactional
    @Modifying
    public boolean darBajaArticulo(Long idArticulo) throws Exception {
        Articulo articulo = articuloRepository.findById(idArticulo).orElseThrow(() -> new Exception("Artículo no encontrado"));
        List<OrdenCompra> ordenesPendientes = ordenCompraRepository.findOrdenCompraByEstadoAndArticulo("PENDIENTE", idArticulo);
        List<OrdenCompra> ordenesEnCurso = ordenCompraRepository.findOrdenCompraByEstadoAndArticulo("EN-CURSO", idArticulo);
        if (!ordenesPendientes.isEmpty() && !ordenesEnCurso.isEmpty()) {
            throw new Exception("El artículo no se puede dar de baja porque tiene órdenes de compra pendientes");
        }
        articuloRepository.deleteDetallesByVenta(idArticulo);
        articuloRepository.deleteDetallesPorArticulo(idArticulo);
        articuloRepository.deletePrediccionesByArticulo(idArticulo);
        articuloRepository.deleteDemandasHistoricasPorArticulo(idArticulo);
        articuloRepository.deleteProveedorArticuloByArticulo(idArticulo);
        baseRepository.deleteById(idArticulo);
        return true;
    }

    @Override
    public List<Articulo> listadoFaltantes(){
        List<Articulo> articulos = articuloRepository.findAll();
        List<Articulo> articulosFaltantes = new ArrayList<>();
        for (Articulo articulo : articulos){
            if (articulo.getStockActual() <= articulo.getStockSeguridad()){
                articulosFaltantes.add(articulo);
            }
        }
        return articulosFaltantes;
    }

    @Override
    public List<Articulo> listadoAReponer(){
        List<Articulo> articulos = articuloRepository.findAll();
        List<Articulo> articulosAReponer = new ArrayList<>();
        for (Articulo articulo : articulos){
            if (articulo.getStockActual() <= articulo.getPuntoPedido()){
                articulosAReponer.add(articulo);
            }
        }
        return articulosAReponer;
    }

    @Override
    public int calcularStockSeguridad(Long articuloId){
        Articulo articulo = findById(articuloId);
        double valorZ = 1.64 ;
        Double tiempoDemora = proveedorArticuloRepository.findProveedorArticuloByProveedorAndArticulo(articulo.getProveedorPred().getId(),articulo.getId()).getTiempoDemora();
        Double tiempoRevision = articulo.getTiempoRevision();
        if (tiempoRevision == null){
            tiempoRevision = 0.0;
        }
        int stockSeguridad = (int) (valorZ * Math.sqrt(tiempoDemora + tiempoRevision));
        articulo.setStockSeguridad(stockSeguridad);
        articuloRepository.save(articulo);
        return stockSeguridad;
    }

    @Override
    public Integer demandaAnual(Long articuloId) throws Exception {
        Articulo articulo = articuloRepository.findById(articuloId).orElseThrow(() -> new Exception("Artículo no encontrado"));
        LocalDate esteAnio = LocalDate.now();
        LocalDate anioPasado = esteAnio.minusYears(1);
        Integer demanda = demandaService.calcularDemanda(anioPasado,esteAnio,articuloId);

        articulo.setDemandaAnual(demanda);
        articuloRepository.save(articulo);
        return demanda;
    }

    @Override
    public Integer calculoPuntoPedido(Long articuloId) throws Exception {
        Articulo articulo = articuloRepository.findById(articuloId).orElseThrow(() -> new Exception("Artículo no encontrado"));
        int demanda = demandaAnual(articuloId);
        Double tiempoDemora = proveedorArticuloRepository.findProveedorArticuloByProveedorAndArticulo(articulo.getProveedorPred().getId(),articulo.getId()).getTiempoDemora();
        Double demandaDiaria = (double)demanda/365;
        int puntoPedido = (int)Math.ceil(demandaDiaria * tiempoDemora)+articulo.getStockSeguridad();
        articulo.setPuntoPedido(puntoPedido);
        articuloRepository.save(articulo);
        return puntoPedido;

    }

    @Override
    public Integer calculoLoteOptimo(Long articuloId) throws Exception {
        Articulo articulo = articuloRepository.findById(articuloId).orElseThrow(() -> new Exception("Artículo no encontrado"));
        Long idProvPred=articulo.getProveedorPred().getId();
        ProveedorArticulo proveedorArticulo=proveedorArticuloRepository.
                findProveedorArticuloByProveedorAndArticulo(idProvPred, articuloId);
        int demanda = demandaAnual(articuloId);
        Double costoP = articulo.getCostoPedido();
        if(costoP==0){
            costoP= proveedorArticulo.getCostoPedido();
            articulo.setCostoPedido(costoP);
        }
        Double costoA = articulo.getCostoAlmacenamiento();
        if (costoA==0){
            costoA = proveedorArticulo.getCostoAlmacenamiento();
            articulo.setCostoAlmacenamiento(costoA);
        }
        int loteOptimo = (int)Math.sqrt((2 * demanda * costoP) / costoA);
        articulo.setLoteOptimo(loteOptimo);
        articuloRepository.save(articulo);
        return loteOptimo;
    }

    @Override
    public Double calculoCGI(Long articuloId) throws Exception {
        Articulo articulo = articuloRepository.findById(articuloId).orElseThrow(() -> new Exception("Artículo no encontrado"));
        Double precioArticulo = proveedorArticuloRepository.findProveedorArticuloByProveedorAndArticulo(articulo.getProveedorPred().getId(),articulo.getId()).getPrecioArticuloProveedor();
        Integer cantidadCompra = 0;

        if(articulo.getModeloInventario() == ModeloInventario.LOTE_FIJO){
            cantidadCompra = articulo.getLoteOptimo();
        } else {
            cantidadCompra = articulo.getCantidadAPedir();
        }
        double costoCompra = precioArticulo * cantidadCompra;
        Double cgi = costoCompra + articulo.getCostoPedido() * (articulo.getDemandaAnual()/2) +
                articulo.getCostoAlmacenamiento() * (cantidadCompra/2);
        articulo.setCgi(cgi);
        articuloRepository.save(articulo);
        return cgi;
    }

    @Override
    public void calculosLoteFijo(Long articuloId) throws Exception {
        Articulo articulo = articuloRepository.findById(articuloId).orElseThrow(() -> new Exception("Artículo no encontrado"));
        Integer loteOptimo = calculoLoteOptimo(articuloId);
        Integer puntoPedido = calculoPuntoPedido(articuloId);
        int stockSeguridad = calcularStockSeguridad(articuloId);
        Double cgi = calculoCGI(articuloId);
        articuloRepository.save(articulo);
    }

    @Override
    public Integer calculoCantidadMax(Long articuloId) throws Exception {
        Articulo articulo = articuloRepository.findById(articuloId).orElseThrow(() -> new Exception("Artículo no encontrado"));
        Double valorZ = 1.64;
        int demanda = demandaAnual(articuloId);
        Double tiempoRevision = articulo.getTiempoRevision();
        Double tiempoDemora = proveedorArticuloRepository.findProveedorArticuloByProveedorAndArticulo(articulo.getProveedorPred().getId(),articulo.getId()).getTiempoDemora();
        int desvEstandarDemandaDiaria = 1;

        Double demandaPromedioDiaria = (double)demanda/365;

        Double desvEstandarTiempoRevisionYDemora = Math.sqrt(tiempoRevision + tiempoDemora) * desvEstandarDemandaDiaria;

        Integer cantidadMaxima = (int) (demandaPromedioDiaria * (tiempoRevision + tiempoDemora) + valorZ * desvEstandarTiempoRevisionYDemora);
        articulo.setCantidadMaxima(cantidadMaxima);
        articuloRepository.save(articulo);
        return cantidadMaxima;
    }

    @Override
    public Integer calculoCantAPedir(Long articuloId) throws Exception {
        Articulo articulo = articuloRepository.findById(articuloId).orElseThrow(() -> new Exception("Artículo no encontrado"));
        Integer cantidadAPedir = articulo.getCantidadMaxima() - articulo.getStockActual();
        articulo.setCantidadAPedir(cantidadAPedir);
        articuloRepository.save(articulo);
        return cantidadAPedir;
    }

    @Override
    public void calculosIntervaloFijo(Long articuloId) throws Exception {
        Articulo articulo = articuloRepository.findById(articuloId).orElseThrow(() -> new Exception("Artículo no encontrado"));
        Integer cantidadMaxima = calculoCantidadMax(articuloId);
        Integer cantidadAPedir = calculoCantAPedir(articuloId);
        int stockSeguridad = calcularStockSeguridad(articuloId);
        Double cgi = calculoCGI(articuloId);
        articuloRepository.save(articulo);

    }


    @Override
    public void modificarModeloInventario(Long articuloId) throws Exception{
        Articulo articulo = findById(articuloId);
        System.out.println("nice");
            if(articulo.getModeloInventario().equals(ModeloInventario.LOTE_FIJO)){
                System.out.println("SoyLF");
                articulo.setLoteOptimo(0);
                articulo.setPuntoPedido(0);
                articulo.setModeloInventario(ModeloInventario.INTERVALO_FIJO);
                calculosIntervaloFijo(articuloId);
                articuloRepository.save(articulo);
            }else if(articulo.getModeloInventario().equals(ModeloInventario.INTERVALO_FIJO)){
                System.out.println("soyIF");
                articulo.setCantidadMaxima(0);
                articulo.setCantidadAPedir(0);
                articulo.setModeloInventario(ModeloInventario.LOTE_FIJO);
                calculosLoteFijo(articuloId);
                articuloRepository.save(articulo);
            }
        System.out.println("sali");

    }

    @Override
    public void modificarValoresProveedor(Long proveedorId,Long articuloId) throws Exception{
        Articulo articulo = articuloRepository.findById(articuloId).orElseThrow(() -> new Exception("Artículo no encontrado"));
        Proveedor proveedor = proveedorRepository.findById(proveedorId).orElseThrow(() -> new Exception("Proveedor no encontrado"));
            ProveedorArticulo nuevoProveedor = proveedorArticuloService. findProveedorArticuloByArticuloAndProveedor(proveedorId, articuloId);
            articulo.setProveedorPred(proveedor);
            articulo.setCostoAlmacenamiento(nuevoProveedor.getCostoAlmacenamiento());
            articulo.setCostoPedido(nuevoProveedor.getCostoPedido());
            articulo.setPrecio(nuevoProveedor.getPrecioArticuloProveedor());
            articuloRepository.save(articulo);

    }

    @Override
    public void calcularTodo(Long articuloId) throws Exception {
        Articulo articulo = articuloRepository.findById(articuloId).orElseThrow(() -> new Exception("Artículo no encontrado"));

        if(articulo.getModeloInventario() == ModeloInventario.LOTE_FIJO){
            articulo.setPuntoPedido(calculoPuntoPedido(articuloId));
            articulo.setLoteOptimo(calculoLoteOptimo(articuloId));
        } else if ((articulo.getModeloInventario() == ModeloInventario.INTERVALO_FIJO)) {

            articulo.setCantidadMaxima(calculoCantidadMax(articuloId));
            articulo.setCantidadAPedir(calculoCantAPedir(articuloId));
        }
        articulo.setDemandaAnual(demandaAnual(articuloId));
        articulo.setStockSeguridad(calcularStockSeguridad(articuloId));
        articulo.setCgi(calculoCGI(articuloId));

        articuloRepository.save(articulo);
    }

}
