package com.example.invOp_Global.controllers;

import com.example.invOp_Global.entities.ProveedorArticulo;
import com.example.invOp_Global.service.ProveedorArticuloServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "api/v1/proveedorArticulo")
public class ProveedorArticuloController extends BaseControllerImpl<ProveedorArticulo, ProveedorArticuloServiceImpl> {


    @GetMapping("/findProveedoresByArticulo/{articulo_id}")
    public ResponseEntity<?> findProveedoresByArticulo(@PathVariable Long articulo_id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(servicio.findProveedoresByArticulo(articulo_id));
        } catch(Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(("{\"error\": \"" + e.getMessage() + "\"}"));
        }
    }

    @GetMapping("/findArticulosByProveedor")
    public ResponseEntity<?> findArticulosByProveedor(@RequestParam Long proveedor_id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(servicio.findArticulosByProveedor(proveedor_id));
        } catch(Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(("{\"error\": \"" + e.getMessage() + "\"}"));
        }
    }
    @GetMapping("/findProveedorArticuloByArticuloAndProveedor")
    public ResponseEntity<?> findProveedorArticuloByArticuloAndProveedor(@RequestParam Long proveedor_id,@RequestParam Long articulo_id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(servicio.findProveedorArticuloByArticuloAndProveedor(proveedor_id, articulo_id));
        } catch(Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(("{\"error\": \"" + e.getMessage() + "\"}"));
        }
    }
}
