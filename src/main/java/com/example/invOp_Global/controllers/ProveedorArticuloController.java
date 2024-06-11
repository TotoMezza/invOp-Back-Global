package com.example.invOp_Global.controllers;

import com.example.invOp_Global.entities.ProveedorArticulo;
import com.example.invOp_Global.service.ProveedorArticuloServiceImpl;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "api/v1/proveedorArticulo")
public class ProveedorArticuloController extends BaseControllerImpl<ProveedorArticulo, ProveedorArticuloServiceImpl> {
}
