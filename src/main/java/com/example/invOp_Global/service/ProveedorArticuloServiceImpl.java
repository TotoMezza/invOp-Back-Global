package com.example.invOp_Global.service;

import com.example.invOp_Global.dtos.CrearPADTO;
import com.example.invOp_Global.entities.Articulo;
import com.example.invOp_Global.entities.Proveedor;
import com.example.invOp_Global.entities.ProveedorArticulo;
import com.example.invOp_Global.repository.ArticuloRepository;
import com.example.invOp_Global.repository.ProveedorArticuloRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProveedorArticuloServiceImpl extends BaseServiceImpl<ProveedorArticulo,Long> implements ProveedorArticuloService {

    @Autowired
    private ProveedorArticuloRepository proveedorArticuloRepository;

    @Autowired
    private ProveedorService proveedorService;

    @Autowired
    private ArticuloRepository articuloRepository;


    public ProveedorArticuloServiceImpl(ProveedorArticuloRepository proveedorArticuloRepository){
        super(proveedorArticuloRepository);
        this.proveedorArticuloRepository = proveedorArticuloRepository;
        this.proveedorService = proveedorService;
    }

    @Override
    @Transactional
    public List<ProveedorArticulo> findProveedoresByArticulo(Long articuloId) throws Exception{
        try {
            return proveedorArticuloRepository.findProveedoresByArticulo(articuloId);
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    public List<ProveedorArticulo> findArticulosByProveedor(Long proveedor_id) {
            List<ProveedorArticulo> buscarArticulos = proveedorArticuloRepository.findArticulosByProveedor(proveedor_id);
            return buscarArticulos;
    }


    public ProveedorArticulo findProveedorArticuloByArticuloAndProveedor(Long proveedorId, Long articuloId){
        return proveedorArticuloRepository.findProveedorArticuloByProveedorAndArticulo(proveedorId,articuloId);
    }

    @Override
    public ProveedorArticulo crearPA(CrearPADTO crearPADTO) throws Exception {
        ProveedorArticulo proveedorArticulo = new ProveedorArticulo();
        Articulo articulo = articuloRepository.findById(crearPADTO.getIdArticulo()).orElseThrow(()-> new Exception("No existe tal artículo"));
        Proveedor proveedor = proveedorService.findById(crearPADTO.getIdProveedor());
        proveedorArticulo.setArticulo(articulo);
        proveedorArticulo.setProveedor(proveedor);
        proveedorArticulo.setPrecioArticuloProveedor(crearPADTO.getPrecioArticuloProveedor());
        proveedorArticulo.setCostoPedido(crearPADTO.getCostoPedido());
        proveedorArticulo.setTiempoDemora(crearPADTO.getTiempoDemora());
        proveedorArticulo.setCostoAlmacenamiento(crearPADTO.getCostoAlmacenamiento());
        proveedorArticuloRepository.save(proveedorArticulo);

        return proveedorArticulo;
    }

}
