package com.example.invOp_Global.controllers;


import com.example.invOp_Global.entities.Demanda;
import com.example.invOp_Global.service.DemandaServiceImpl;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "api/v1/demanda")
public class DemandaController extends BaseControllerImpl<Demanda, DemandaServiceImpl>{
}
