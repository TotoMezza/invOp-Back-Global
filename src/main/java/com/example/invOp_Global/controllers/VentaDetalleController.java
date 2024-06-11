package com.example.invOp_Global.controllers;

import com.example.invOp_Global.entities.VentaDetalle;
import com.example.invOp_Global.service.VentaDetalleServiceImpl;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "api/v1/ventasdetalles")
public class VentaDetalleController extends BaseControllerImpl<VentaDetalle, VentaDetalleServiceImpl>{

}
