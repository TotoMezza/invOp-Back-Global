package com.example.invOp_Global.controllers;

import com.example.invOp_Global.dtos.ParametrosPrediccionDTO;
import com.example.invOp_Global.entities.PrediccionDemanda;
import com.example.invOp_Global.service.PrediccionDemandaService;
import com.example.invOp_Global.service.PrediccionDemandaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "api/v1/prediccionDemanda")
public class PrediccionDemandaController extends BaseControllerImpl<PrediccionDemanda, PrediccionDemandaServiceImpl>{

    @Autowired
    private PrediccionDemandaService prediccionDemandaService;

    @GetMapping("/find/{articuloId}")
    public ResponseEntity<List<PrediccionDemanda>> findPrediccionDemandaArticulo(@PathVariable Long articuloId) throws Exception {
        List<PrediccionDemanda> prediccionDemandaList = prediccionDemandaService.findPrediccionDemandaArticulo(articuloId);
        return ResponseEntity.ok(prediccionDemandaList);
    }

    @PostMapping("/calculo-pmp")
    public ResponseEntity<Integer> calculoPMPonderado(@RequestBody ParametrosPrediccionDTO parametrosPrediccionDTO) throws Exception {
        Integer resultado = prediccionDemandaService.calculoPMPonderado(parametrosPrediccionDTO);
        return ResponseEntity.ok(resultado);
    }

    @PostMapping("/calculo-pmps")
    public ResponseEntity<Integer> calculoPMPSuavizado(@RequestBody ParametrosPrediccionDTO parametrosPrediccionDTO) throws Exception {
        Integer resultado = prediccionDemandaService.calculoPMPSuavizado(parametrosPrediccionDTO);
        return ResponseEntity.ok(resultado);
    }

    @PostMapping("/calculo-pe")
    public ResponseEntity<Integer> calculoPEstacional(@RequestBody ParametrosPrediccionDTO parametrosPrediccionDTO) throws Exception {
        Integer resultado = prediccionDemandaService.calculoPEstacional(parametrosPrediccionDTO);
        return ResponseEntity.ok(resultado);
    }

    @PostMapping("/calculo-rl")
    public ResponseEntity<Integer> calculoRegresionLineal(@RequestBody ParametrosPrediccionDTO parametrosPrediccionDTO) throws Exception {
        Integer resultado = prediccionDemandaService.calculoRegresionLineal(parametrosPrediccionDTO);
        return ResponseEntity.ok(resultado);
    }

    @PostMapping("/crear-prediccion")
    public ResponseEntity<List<PrediccionDemanda>> crearPrediccion(@RequestBody ParametrosPrediccionDTO parametrosPrediccionDTO) throws Exception {
        List<PrediccionDemanda> prediccionDemandaList = prediccionDemandaService.crearPrediccion(parametrosPrediccionDTO);
        return ResponseEntity.ok(prediccionDemandaList);
    }

}
