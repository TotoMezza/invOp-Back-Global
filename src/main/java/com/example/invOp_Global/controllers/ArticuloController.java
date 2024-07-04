package com.example.invOp_Global.controllers;

import com.example.invOp_Global.dtos.CrearArticuloDTO;
import com.example.invOp_Global.dtos.FaltanteDto;
import com.example.invOp_Global.dtos.ReponerDto;
import com.example.invOp_Global.entities.Articulo;
import com.example.invOp_Global.service.ArticuloService;
import com.example.invOp_Global.service.ArticuloServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "api/v1/articulos")
public class ArticuloController extends BaseControllerImpl<Articulo, ArticuloServiceImpl> {

    @Autowired
    private ArticuloService articuloService;


    @Autowired
    public ArticuloController(ArticuloService articuloService) {
        this.articuloService = articuloService;
    }


    @DeleteMapping("/baja/{idArticulo}")
    public ResponseEntity<?> darBajaArticulo(@PathVariable Long idArticulo) {
        try {
            boolean resultado = articuloService.darBajaArticulo(idArticulo);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(resultado);
        } catch (Exception e) {
            // Agregar registro del error para diagnosticar el problema
            e.printStackTrace();
            // Proporcionar un mensaje de error más específico basado en el mensaje de excepción
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"" + e.getMessage() + "\"}");
        }
    }


    @GetMapping("/faltantes")
    public ResponseEntity<List<FaltanteDto>> getArticulosFaltantes() {
            List<Articulo> articulosFaltantes = articuloService.listadoFaltantes();
            List<FaltanteDto> faltantes = new ArrayList<>();
            for(Articulo articulo : articulosFaltantes){
                FaltanteDto faltante = new FaltanteDto();
                faltante.setArticuloId(articulo.getId());
                faltante.setNombre(articulo.getNombre());
                faltante.setStockActual(articulo.getStockActual());
                faltante.setStockSeguridad(articulo.getStockSeguridad());
                faltantes.add(faltante);
            }
            return ResponseEntity.ok(faltantes);
    }

    @GetMapping("/reponer")
    public ResponseEntity<List<ReponerDto>> getArticulosAReponer() {
        List<Articulo> articulosAReponer = articuloService.listadoAReponer();
        List<ReponerDto> ReponerFinal = new ArrayList<>();
        for (Articulo articulo : articulosAReponer) {
            ReponerDto areponer = new ReponerDto();
            areponer.setArticuloId(articulo.getId());
            areponer.setNombre(articulo.getNombre());
            areponer.setStockActual(articulo.getStockActual());
            areponer.setPuntoPedido(articulo.getPuntoPedido());
            ReponerFinal.add(areponer);
        }
        return ResponseEntity.ok(ReponerFinal);
    }

    @PutMapping("/modelo-inventario/{articuloId}")
    public ResponseEntity<?> modificarModeloInventario(@PathVariable Long articuloId) throws Exception {
        try{
            articuloService.modificarModeloInventario(articuloId);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PutMapping("/valores-proveedor/{proveedorId}/{articuloId}")
    public ResponseEntity<?> modificarValoresProveedor(@PathVariable Long proveedorId, @PathVariable Long articuloId) throws Exception {

        try {
             articuloService.modificarValoresProveedor(proveedorId, articuloId);
            return ResponseEntity.status(HttpStatus.OK).build();
        }catch (Exception e){
            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }

    }
    @PutMapping("/calculos/{id}")
    public ResponseEntity<?> calcularAtributos(@PathVariable Long id) throws Exception {
        try{
            articuloService.calcularTodo(id);
            Articulo articulo= articuloService.findById(id);
            return ResponseEntity.status(HttpStatus.OK).body(articulo);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error inesperado: " + e.getMessage());
        }
    }

    @PostMapping("/crear")
    public ResponseEntity<?> crearArticulo(@RequestBody CrearArticuloDTO crearArticuloDTO) throws Exception{
        try{
            Articulo articulo = articuloService.crearArticulo(crearArticuloDTO);
            return ResponseEntity.status(HttpStatus.OK).body(articulo);
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error inesperado: "+ e.getMessage());
        }
    }
}
