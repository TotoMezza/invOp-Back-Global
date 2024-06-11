package com.example.invOp_Global.controllers;

import com.example.invOp_Global.entities.Prediccion;
import com.example.invOp_Global.service.PrediccionServiceImpl;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "api/v1/prediccion")
public class PredicciónController extends BaseControllerImpl<Prediccion, PrediccionServiceImpl>{
}
