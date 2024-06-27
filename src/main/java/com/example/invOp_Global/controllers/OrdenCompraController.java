package com.example.invOp_Global.controllers;

import com.example.invOp_Global.dtos.CrearOCDto;
import com.example.invOp_Global.dtos.ModificarOCDto;
import com.example.invOp_Global.entities.Articulo;
import com.example.invOp_Global.entities.OrdenCompra;
import com.example.invOp_Global.service.OrdenCompraService;
import com.example.invOp_Global.service.OrdenCompraServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "api/v1/ordenCompra")
public class OrdenCompraController extends BaseControllerImpl<OrdenCompra, OrdenCompraServiceImpl> {

    @Autowired
    private OrdenCompraService ordenCompraService;

    @GetMapping("/findOrdenesByEstado")
    public ResponseEntity<?> findOrdenesByEstado(@RequestParam String filtroEstado) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(servicio.findOrdenCompraByEstado(filtroEstado));
        } catch(Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(("{\"error\": \"" + e.getMessage() + "\"}"));
        }
    }

    @PostMapping("/crear")
    public ResponseEntity<OrdenCompra> crearOrdenCompra(@RequestBody Articulo articulo) {
        OrdenCompra ordenCompra = ordenCompraService.crearOrdenCompra(articulo);
        return ResponseEntity.ok(ordenCompra);
    }
    @PostMapping("/nueva")
    public ResponseEntity<OrdenCompra> nuevaOC(@RequestBody CrearOCDto crearOCDto){
        OrdenCompra ordenCompra = ordenCompraService.nuevaOC(crearOCDto);
        return ResponseEntity.ok(ordenCompra);
    }

    @PutMapping("/en-curso/{ordenCompraId}")
    public ResponseEntity<Void> ordenEnCurso(@PathVariable Long ordenCompraId) throws Exception {
        ordenCompraService.ordenEnCurso(ordenCompraId);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/finalizar/{ordenCompraId}")
    public ResponseEntity<Void> ordenFinalizada(@PathVariable Long ordenCompraId) throws Exception {
        ordenCompraService.ordenFinalizada(ordenCompraId);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/cancelar/{ordenCompraId}")
    public ResponseEntity<Void> cancelarOrden(@PathVariable Long ordenCompraId) throws Exception {
        ordenCompraService.cancelarOrden(ordenCompraId);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/modificar")
    public ResponseEntity<?> modificarOC(@RequestBody ModificarOCDto modificarOCDto) throws Exception{
        ordenCompraService.modificarOC(modificarOCDto);
        return ResponseEntity.ok().build();
    }

}
