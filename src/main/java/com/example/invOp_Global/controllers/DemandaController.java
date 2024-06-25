package com.example.invOp_Global.controllers;


import com.example.invOp_Global.entities.Demanda;
import com.example.invOp_Global.dtos.DemandaDto;
import com.example.invOp_Global.service.DemandaService;
import com.example.invOp_Global.service.DemandaServiceImpl;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.time.LocalDate;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "api/v1/demanda")
public class DemandaController extends BaseControllerImpl<Demanda, DemandaServiceImpl>{

    @Autowired
    private DemandaService demandaService;

    @PostMapping("/calcularDemanda")
    public ResponseEntity<?> calcularDemanda(@RequestBody DemandaDto demanda) throws Exception {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            LocalDate fechaDesde = demanda.getFechaDesde();
            LocalDate fechaHasta = demanda.getFechaHasta();
            return ResponseEntity.status(HttpStatus.OK).body(demandaService.nuevaDemanda(fechaDesde, fechaHasta, demanda.getArticuloId()));
        } catch(EntityNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Articulo no encontrado");
        } catch (IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Parametros inválidos");
        } catch(Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocurrió un error inesperado");
        }
    }

}
