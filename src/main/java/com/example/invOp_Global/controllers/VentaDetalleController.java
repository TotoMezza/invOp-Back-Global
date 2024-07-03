package com.example.invOp_Global.controllers;

import com.example.invOp_Global.entities.VentaDetalle;
import com.example.invOp_Global.service.DetalleOCService;
import com.example.invOp_Global.service.VentaDetalleService;
import com.example.invOp_Global.service.VentaDetalleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "api/v1/ventasdetalles")
public class VentaDetalleController extends BaseControllerImpl<VentaDetalle, VentaDetalleServiceImpl>{

    @Autowired
    private VentaDetalleService ventaDetalleService;


    @GetMapping("/detallesVenta/{ventaId}")
    public ResponseEntity<?> findDetallesVenta(@PathVariable Long ventaId)throws Exception{
        try{
            ventaDetalleService.findDetallesVenta(ventaId);
            return ResponseEntity.status(HttpStatus.OK).body(servicio.findDetallesVenta(ventaId));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error interno del servidor: "+ e.getMessage());
        }
    }

}
