package com.example.invOp_Global.controllers;

import com.example.invOp_Global.dtos.CrearPADTO;
import com.example.invOp_Global.entities.ProveedorArticulo;
import com.example.invOp_Global.service.ProveedorArticuloServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "api/v1/proveedorarticulo")
public class ProveedorArticuloController extends BaseControllerImpl<ProveedorArticulo, ProveedorArticuloServiceImpl> {

    @Autowired
    ProveedorArticuloServiceImpl proveedorArticuloService;

    @GetMapping("/findProveedoresByArticulo/{articuloId}")
    public ResponseEntity<?> findProveedoresByArticulo(@PathVariable Long articuloId) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(servicio.findProveedoresByArticulo(articuloId));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(("{\"error\": \"" + e.getMessage() + "\"}"));
        }
    }

    @GetMapping("/findArticulosByProveedor/{proveedor_id}")
    public ResponseEntity<?> findArticulosByProveedor(@PathVariable Long proveedor_id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(servicio.findArticulosByProveedor(proveedor_id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(("{\"error\": \"" + e.getMessage() + "\"}"));
        }
    }

    @GetMapping("/findProveedorArticuloByArticuloAndProveedor/{proveedorId}/{articuloId}")
    public ResponseEntity<?> findProveedorArticuloByArticuloAndProveedor(@PathVariable Long proveedorId, @PathVariable Long articuloId) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(servicio.findProveedorArticuloByArticuloAndProveedor(proveedorId, articuloId));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(("{\"error\": \"" + e.getMessage() + "\"}"));
        }
    }

    @PostMapping("/crear")
    public ResponseEntity<?> crearPA (@RequestBody CrearPADTO crearPADTO) throws Exception {
        proveedorArticuloService.crearPA(crearPADTO);
        return ResponseEntity.ok().build();
    }
}

