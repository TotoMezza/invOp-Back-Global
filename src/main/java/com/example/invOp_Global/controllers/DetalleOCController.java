package com.example.invOp_Global.controllers;

import com.example.invOp_Global.entities.DetalleOrdenCompra;
import com.example.invOp_Global.service.DetalleOCServiceImpl;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "api/v1/detallesOrdenCompra")
public class DetalleOCController extends BaseControllerImpl<DetalleOrdenCompra, DetalleOCServiceImpl> {
}
