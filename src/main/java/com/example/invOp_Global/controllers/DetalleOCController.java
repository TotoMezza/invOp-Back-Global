package com.example.invOp_Global.controllers;

import com.example.invOp_Global.entities.DetalleOrdenCompra;
import com.example.invOp_Global.service.DetalleOCService;
import com.example.invOp_Global.service.DetalleOCServiceImpl;
import com.example.invOp_Global.service.OrdenCompraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "api/v1/detallesOrdenCompra")
public class DetalleOCController extends BaseControllerImpl<DetalleOrdenCompra, DetalleOCServiceImpl> {

    @Autowired
    private DetalleOCService detalleOCService;

    @GetMapping("/detallesOC/{ordenCompraId}")
    public ResponseEntity<?> findDetallesOC(@PathVariable Long ordenCompraId) throws Exception{
        try{
            detalleOCService.findDetallesOC(ordenCompraId);
            return ResponseEntity.status(HttpStatus.OK).body(servicio.findDetallesOC(ordenCompraId));
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error inesperado: " + e.getMessage());
        }

    }

}
