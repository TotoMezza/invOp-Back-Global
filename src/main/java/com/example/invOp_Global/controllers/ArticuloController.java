package com.example.invOp_Global.controllers;

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
    public ResponseEntity<Void> darBajaArticulo(@PathVariable Long idArticulo) throws Exception {
        articuloService.darBajaArticulo(idArticulo);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/faltantes")
    public ResponseEntity<List<FaltanteDto>> getArticulosFaltantes() {
        try {
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
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/reponer")
    public ResponseEntity<List<ReponerDto>> getArticulosAReponer() {
        try {
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
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
