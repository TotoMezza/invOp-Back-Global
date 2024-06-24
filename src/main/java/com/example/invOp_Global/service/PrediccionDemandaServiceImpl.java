package com.example.invOp_Global.service;

import com.example.invOp_Global.dtos.ParametrosPrediccionDTO;
import com.example.invOp_Global.entities.Articulo;
import com.example.invOp_Global.entities.PrediccionDemanda;

import com.example.invOp_Global.repository.BaseRepository;
import com.example.invOp_Global.repository.PrediccionDemandaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PrediccionDemandaServiceImpl extends BaseServiceImpl<PrediccionDemanda,Long> implements PrediccionDemandaService{


    @Autowired
    private DemandaService demandaService;

    @Autowired
    private PrediccionDemandaRepository prediccionDemandaRepository;

    @Autowired
    private ArticuloService articuloService;


    public PrediccionDemandaServiceImpl(BaseRepository<PrediccionDemanda, Long> baseRepository, PrediccionDemandaRepository prediccionDemandaRepository) {
        super(baseRepository);
        this.prediccionDemandaRepository=prediccionDemandaRepository;
        this.demandaService = demandaService;
        this.articuloService=articuloService;

    }

   @Override
    public List<PrediccionDemanda> buscarPrediccionDemanda(Long filroArticulo) throws  Exception{
        try{
            List<PrediccionDemanda> listaPredicciones = prediccionDemandaRepository.prediccionesPorArticulo(filroArticulo);
            return listaPredicciones;
        }catch(Exception e){
            throw new Exception(e.getMessage());
        }
    }

    //Metodo para crear prediccion
    public List<PrediccionDemanda> crearPrediccion(ParametrosPrediccionDTO parametros) throws Exception{
        try{
            Articulo articulo = articuloService.findById(parametros.getArticuloId());
            List<PrediccionDemanda> listaPredicciones = new ArrayList<>();
            for(int i=0; i<parametros.getCantidadPeriodos();i++){

            }
            return listaPredicciones;
        }catch(Exception e){

        }

    }

}
