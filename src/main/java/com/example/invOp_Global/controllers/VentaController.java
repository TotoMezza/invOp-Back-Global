package com.example.invOp_Global.controllers;

import com.example.invOp_Global.entities.Venta;
import com.example.invOp_Global.service.ArticuloService;
import com.example.invOp_Global.service.VentaService;
import com.example.invOp_Global.service.VentaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.time.LocalDate;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "api/v1/ventas")
public class VentaController extends BaseControllerImpl<Venta, VentaServiceImpl>{

    @Autowired
    private ArticuloService articuloService;
    @Autowired
    private VentaService ventaService;


    @GetMapping("/findVentasByFechas")
    public ResponseEntity<?> findVentasByFechas(@RequestParam LocalDate desde, @RequestParam LocalDate hasta) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSSSS");
        try {
            LocalDate fechaDesde = desde;
            LocalDate fechaHasta = hasta;
            return ResponseEntity.status(HttpStatus.OK).body(servicio.findVentasByFechas(fechaDesde, fechaHasta));
        } catch(Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(("{\"error\": \"" + e.getMessage() + "\"}"));
        }
    }


}