package com.example.invOp_Global.controllers;

import com.example.invOp_Global.entities.PrediccionDemanda;
import com.example.invOp_Global.service.PrediccionDemandaServiceImpl;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "api/v1/prediccionDemanda")
public class PrediccionDemandaController extends BaseControllerImpl<PrediccionDemanda, PrediccionDemandaServiceImpl>{
}
