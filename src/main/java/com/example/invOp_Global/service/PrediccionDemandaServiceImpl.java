package com.example.invOp_Global.service;

import com.example.invOp_Global.dtos.ParametrosPrediccionDTO;
import com.example.invOp_Global.entities.PrediccionDemanda;

import com.example.invOp_Global.repository.ArticuloRepository;
import com.example.invOp_Global.repository.BaseRepository;
import com.example.invOp_Global.repository.PrediccionDemandaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

import static com.example.invOp_Global.enums.MetodoPrediccion.ESTACIONALIDAD;

@Service
public class PrediccionDemandaServiceImpl extends BaseServiceImpl<PrediccionDemanda,Long> implements PrediccionDemandaService {


    @Autowired
    private DemandaService demandaService;

    @Autowired
    private PrediccionDemandaRepository prediccionDemandaRepository;

    @Autowired
    private ArticuloService articuloService;

    @Autowired
    private ArticuloRepository articuloRepository;


    public PrediccionDemandaServiceImpl(BaseRepository<PrediccionDemanda, Long> baseRepository, PrediccionDemandaRepository prediccionDemandaRepository) {
        super(baseRepository);
        this.prediccionDemandaRepository = prediccionDemandaRepository;
        this.demandaService = demandaService;
        this.articuloService = articuloService;
        this.articuloRepository = articuloRepository;

    }


    public List<PrediccionDemanda> findPrediccionDemandaArticulo(Long articuloId) throws Exception {
        try {
            List<PrediccionDemanda> listaPredicciones = prediccionDemandaRepository.prediccionesPorArticulo(articuloId);
            return listaPredicciones;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public PrediccionDemanda findPrediccionDemandaArticuloByFechas(Long articuloId, int anio, int mes) throws Exception {
        try {
            PrediccionDemanda prediccion = prediccionDemandaRepository.prediccionPorArticuloAndFechas(articuloId, anio, mes);
            return prediccion;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Integer prediccionPMPonderado(ParametrosPrediccionDTO parametrosPrediccionDTO) throws Exception {
        try {
            // Verificar que la cantidad de periodos históricos coincida con la cantidad de coeficientes de ponderación
            if (parametrosPrediccionDTO.getCantidadPeriodosAUsar() != parametrosPrediccionDTO.getCoeficientes().size()) {
                throw new IllegalArgumentException("La cantidad de periodos a utilizar debe coincidir con la cantidad de coeficientes");
            }
            LocalDate fechaPrediccion = LocalDate.of(parametrosPrediccionDTO.getAnioPrediccion(), parametrosPrediccionDTO.getMesPrediccion(), 1);
            double valoresPonderados = 0.0;
            double coeficientes = 0.0;
            int i = 0;
            for (Double factorPonderacion : parametrosPrediccionDTO.getCoeficientes()) {
                LocalDate fechaDesde = fechaPrediccion.minusMonths(i + 1).withDayOfMonth(1);
                LocalDate fechaHasta = fechaPrediccion.minusMonths(i + 1).withDayOfMonth(fechaPrediccion.minusMonths(i + 1).lengthOfMonth());
                int demanda = demandaService.calcularDemandaHistorica(fechaDesde, fechaHasta, parametrosPrediccionDTO.getArticuloId());
                if (demanda <= 0) {
                    int anio = fechaDesde.getYear();
                    int mes = fechaDesde.getMonthValue();
                    PrediccionDemanda prediccionMesAnterior = prediccionDemandaRepository.prediccionPorArticuloAndFechas(parametrosPrediccionDTO.getArticuloId(), anio, mes);
                    if (!prediccionMesAnterior.equals(null)) {
                        demanda = prediccionMesAnterior.getValorPrediccion();
                    } else {
                        demanda = 0;
                    }
                }
                valoresPonderados = valoresPonderados + (factorPonderacion * demanda);
                coeficientes = coeficientes + factorPonderacion;
                i++;
            }
            Integer valorPrediccion = (int) (Math.ceil(valoresPonderados) / coeficientes);
            return valorPrediccion;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public Integer calcularPMMesAnterior(Long idArticulo, LocalDate fechaPrediccion) throws Exception{
        try{
            LocalDate fechaDesde = fechaPrediccion.minusMonths(12).withDayOfMonth(1);
            LocalDate fechaHasta = fechaPrediccion.minusMonths(1).withDayOfMonth(fechaPrediccion.minusMonths(1).lengthOfMonth());
            int demanda = demandaService.calcularDemandaHistorica(fechaDesde, fechaHasta, idArticulo);
            if(demanda <0){
                demanda = 0;
            }
            Integer valorPrediccion = demanda/12;
            return valorPrediccion;
        }catch(Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Integer calculoPMPSuavizado(ParametrosPrediccionDTO parametrosPrediccionDTO) throws Exception{
        try{
            LocalDate fechaPrediccion = LocalDate.of(parametrosPrediccionDTO.getAnioPrediccion(), parametrosPrediccionDTO.getMesPrediccion(), 1);
            LocalDate fechaDesde = fechaPrediccion.minusMonths(1).withDayOfMonth(1);
            LocalDate fechaHasta = fechaPrediccion.minusMonths(1).withDayOfMonth(fechaPrediccion.minusMonths(1).lengthOfMonth());
            int demandaMesAnterior = demandaService.calcularDemandaHistorica(fechaDesde, fechaHasta, parametrosPrediccionDTO.getArticuloId());
            if (demandaMesAnterior <= 0){
                int anio = fechaDesde.getYear();
                int mes = fechaDesde.getMonthValue();
                PrediccionDemanda prediccionMesAnterior = prediccionDemandaRepository.prediccionPorArticuloAndFechas(parametrosPrediccionDTO.getArticuloId(), anio, mes);
                if(!prediccionMesAnterior.equals(null)){
                    demandaMesAnterior = prediccionMesAnterior.getValorPrediccion();
                } else{
                    demandaMesAnterior = 0;
                }
            }
            Double alfa = parametrosPrediccionDTO.getAlfa();
            Integer valorPrediccionMesAnterior = calcularPMMesAnterior(parametrosPrediccionDTO.getArticuloId(), fechaPrediccion.minusMonths(1));
            Integer valorPrediccion = (int)(valorPrediccionMesAnterior + (alfa * (demandaMesAnterior - valorPrediccionMesAnterior)));
            return valorPrediccion;

        }catch(Exception e){
            throw new Exception(e.getMessage());
        }
    }
/*
    @Override
    public Integer calculoPEstacional(ParametrosPrediccionDTO parametrosPrediccionDTO) throws Exception {
        int cantidadMeses = 12;
        ArrayList<Integer> prediccionDemandaMensual = new ArrayList<Integer>(Collections.nCopies(cantidadMeses, 0));
        try {
            int anioActual = 0;
            int cantidadAnios = 3;
            Integer[][] demandaAnios = new Integer[cantidadAnios][cantidadMeses];
            Integer sumatoria = 0;
            for (int i = 0; i < cantidadAnios; i++) {
                anioActual = parametrosPrediccionDTO.getAnioPrediccion() - (i + 1);
                for (int j = 0; j < cantidadMeses; j++) {
                    int mes = j + 1;
                    LocalDate fechaDesde = LocalDate.of(anioActual, mes, 1);
                    LocalDate fechaHasta = LocalDate.of(anioActual, mes, fechaDesde.lengthOfMonth());
                    Integer demandaMesActual = demandaService.calcularDemandaHistorica(fechaDesde, fechaHasta, parametrosPrediccionDTO.getArticuloId());
                    if (demandaMesActual < 0) {
                        demandaMesActual = 0;
                    }
                    demandaAnios[i][j] = demandaMesActual;
                    sumatoria = sumatoria + demandaMesActual;
                }
            }
            Double[] demandaAnualPromedio = new Double[cantidadMeses];
            Arrays.fill(demandaAnualPromedio, 0.0);
            Double demandaMensualPromedio = Double.valueOf((double) sumatoria / (cantidadAnios * cantidadMeses));
            for (int t = 0; t < cantidadMeses; t++) {
                Integer sumaMes = 0;
                for (int k = 0; k < cantidadAnios; k++) {
                    sumaMes = sumaMes + demandaAnios[k][t];
                }
                demandaAnualPromedio[t] = Double.valueOf((double) sumaMes / cantidadAnios);
            }
            System.out.println("demandaAnualPromedio:"+Arrays.toString(demandaAnualPromedio));
            Double[] indiceEstacionalMensual = new Double[cantidadMeses];
            for (int t = 0; t < cantidadMeses; t++) {
                int mes = t + 1;
                indiceEstacionalMensual[t] = demandaAnualPromedio[t] / demandaMensualPromedio;
                prediccionDemandaMensual.set(t, (int) Math.ceil(indiceEstacionalMensual[t] * parametrosPrediccionDTO.getCantidadDemandaAnualTotal()/cantidadMeses));
                PrediccionDemanda prediccionDemanda = new PrediccionDemanda();
                prediccionDemanda.setArticulo(articuloService.findById(datosPrediccionDTO.getIdArticulo()));
                prediccionDemanda.setValorPrediccion(prediccionDemandaMensual.get(t));
                prediccionDemanda.setFechaPrediccion(LocalDate.of(datosPrediccionDTO.getAnioAPredecir(), mes, 1));
                prediccionDemanda.setNombreMetodoUsado(ESTACIONALIDAD);
                prediccionDemandaRepository.save(prediccionDemanda);

            }
            System.out.println("indiceEstacionalMensual: "+  Arrays.toString(indiceEstacionalMensual));
            System.out.println("prediccionPorMes" + prediccionDemandaMensual);

        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
        return prediccionDemandaMensual.get(datosPrediccionDTO.getMesAPredecir()-1);
    }




*/
}
