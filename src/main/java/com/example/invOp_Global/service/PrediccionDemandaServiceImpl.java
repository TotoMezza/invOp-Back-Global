package com.example.invOp_Global.service;

import com.example.invOp_Global.dtos.ParametrosPrediccionDTO;
import com.example.invOp_Global.entities.Articulo;
import com.example.invOp_Global.entities.PrediccionDemanda;

import com.example.invOp_Global.enums.MetodoPrediccion;
import com.example.invOp_Global.repository.ArticuloRepository;
import com.example.invOp_Global.repository.PrediccionDemandaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

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


    public PrediccionDemandaServiceImpl(PrediccionDemandaRepository prediccionDemandaRepository) {
        super(prediccionDemandaRepository);
        this.prediccionDemandaRepository = prediccionDemandaRepository;
        this.demandaService = demandaService;
        this.articuloService = articuloService;
        this.articuloRepository = articuloRepository;
        this.articuloService = articuloService;

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
    public Integer calculoPMPonderado(ParametrosPrediccionDTO parametrosPrediccionDTO) throws Exception {
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
                int demanda = demandaService.calcularDemanda(fechaDesde, fechaHasta, parametrosPrediccionDTO.getArticuloId());
                if (demanda <= 0) {
                    int anio = fechaDesde.getYear();
                    int mes = fechaDesde.getMonthValue();
                    PrediccionDemanda prediccionMesAnterior = prediccionDemandaRepository.prediccionPorArticuloAndFechas(parametrosPrediccionDTO.getArticuloId(), anio, mes);
                    if (prediccionMesAnterior != null) {
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

    public Integer calcularPMMesAnterior(Long idArticulo, LocalDate fechaPrediccion) throws Exception {
        try {
            LocalDate fechaDesde = fechaPrediccion.minusMonths(12).withDayOfMonth(1);
            LocalDate fechaHasta = fechaPrediccion.minusMonths(1).withDayOfMonth(fechaPrediccion.minusMonths(1).lengthOfMonth());
            int demanda = demandaService.calcularDemanda(fechaDesde, fechaHasta, idArticulo);
            if (demanda < 0) {
                demanda = 0;
            }
            Integer valorPrediccion = demanda / 12;
            return valorPrediccion;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Integer calculoPMPSuavizado(ParametrosPrediccionDTO parametrosPrediccionDTO) throws Exception {
        try {
            LocalDate fechaPrediccion = LocalDate.of(parametrosPrediccionDTO.getAnioPrediccion(), parametrosPrediccionDTO.getMesPrediccion(), 1);
            LocalDate fechaDesde = fechaPrediccion.minusMonths(1).withDayOfMonth(1);
            LocalDate fechaHasta = fechaPrediccion.minusMonths(1).withDayOfMonth(fechaPrediccion.minusMonths(1).lengthOfMonth());
            int demandaMesAnterior = demandaService.calcularDemanda(fechaDesde, fechaHasta, parametrosPrediccionDTO.getArticuloId());
            if (demandaMesAnterior <= 0) {
                int anio = fechaDesde.getYear();
                int mes = fechaDesde.getMonthValue();
                PrediccionDemanda prediccionMesAnterior = prediccionDemandaRepository.prediccionPorArticuloAndFechas(parametrosPrediccionDTO.getArticuloId(), anio, mes);
                if (!prediccionMesAnterior.equals(null)) {
                    demandaMesAnterior = prediccionMesAnterior.getValorPrediccion();
                } else {
                    demandaMesAnterior = 0;
                }
            }
            Double alfa = parametrosPrediccionDTO.getAlfa();
            Integer valorPrediccionMesAnterior = calcularPMMesAnterior(parametrosPrediccionDTO.getArticuloId(), fechaPrediccion.minusMonths(1));
            Integer valorPrediccion = (int) (valorPrediccionMesAnterior + (alfa * (demandaMesAnterior - valorPrediccionMesAnterior)));
            return valorPrediccion;

        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Integer calculoPEstacional(ParametrosPrediccionDTO parametrosPrediccionDTO) throws Exception {
        int cantidadMeses = 12;
        List<Integer> prediccionDemandaMensual = new ArrayList<>(cantidadMeses);
        for (int i = 0; i < cantidadMeses; i++) {
            prediccionDemandaMensual.add(0);
        }
        int cantidadAnios = 3;
        Integer[][] demandaAnios = new Integer[cantidadAnios][cantidadMeses];
        Integer sumatoria = 0;
        for (int i = 0; i < cantidadAnios; i++) {
            int anio = parametrosPrediccionDTO.getAnioPrediccion() - (i + 1);
            for (int j = 0; j < cantidadMeses; j++) {
                int mes = j + 1;
                LocalDate fechaDesde = LocalDate.of(anio, mes, 1);
                LocalDate fechaHasta = LocalDate.of(anio, mes, fechaDesde.lengthOfMonth());
                Integer demandaMesActual = demandaService.calcularDemanda(fechaDesde, fechaHasta, parametrosPrediccionDTO.getArticuloId());
                if (demandaMesActual < 0) {
                    demandaMesActual = 0;
                }
                demandaAnios[i][j] = demandaMesActual;
                sumatoria += demandaMesActual;
            }
        }
        double demandaMensualPromedio = (double) sumatoria / (cantidadAnios * cantidadMeses);
        double[] demandaAnualPromedio = new double[cantidadMeses];
        for (int t = 0; t < cantidadMeses; t++) {
            int sumaMes = 0;
            for (int k = 0; k < cantidadAnios; k++) {
                sumaMes += demandaAnios[k][t];
            }
            demandaAnualPromedio[t] = (double) sumaMes / cantidadAnios;
        }
        Double[] indiceEstacionalMensual = new Double[cantidadMeses];
        for (int t = 0; t < cantidadMeses; t++) {
            int mes = t + 1;
            indiceEstacionalMensual[t] = demandaAnualPromedio[t] / demandaMensualPromedio;
            prediccionDemandaMensual.set(t, (int) Math.ceil(indiceEstacionalMensual[t] * parametrosPrediccionDTO.getCantidadDemandaAnual() / cantidadMeses));
            PrediccionDemanda prediccion = new PrediccionDemanda();
            prediccion.setArticulo(articuloService.findById(parametrosPrediccionDTO.getArticuloId()));
            prediccion.setValorPrediccion(prediccionDemandaMensual.get(t));
            prediccion.setFechaPrediccion(LocalDate.of(parametrosPrediccionDTO.getAnioPrediccion(), mes, 1));
            prediccion.setMetodoPrediccion(MetodoPrediccion.ESTACIONALIDAD);
            prediccionDemandaRepository.save(prediccion);
        }

        return prediccionDemandaMensual.get(parametrosPrediccionDTO.getMesPrediccion() - 1);
    }

    @Override
    public Integer calculoRegresionLineal(ParametrosPrediccionDTO parametrosPrediccionDTO) throws Exception {
        try {
            int mesAPredecir = parametrosPrediccionDTO.getMesPrediccion();
            int cantidadPeriodosAUsar = parametrosPrediccionDTO.getCantidadPeriodosAUsar();

            int sumaPeriodos = 0;
            double sumaDemandas = 0.0;
            int sumaXY = 0;
            double sumaX2 = 0.0;
            double a = 0.0;
            double b = 0.0;
            LocalDate fechaPrediccion = LocalDate.of(parametrosPrediccionDTO.getAnioPrediccion(), parametrosPrediccionDTO.getMesPrediccion(), 1);

            for (int i = 0; i < cantidadPeriodosAUsar; i++) {
                LocalDate fechaDesde = fechaPrediccion.minusMonths(i + 1).withDayOfMonth(1);
                LocalDate fechaHasta = fechaPrediccion.minusMonths(i + 1).withDayOfMonth(fechaPrediccion.minusMonths(i + 1).lengthOfMonth());

                int nroMes = cantidadPeriodosAUsar - i;
                int demandaHistoricaMes = demandaService.calcularDemanda(fechaDesde, fechaHasta, parametrosPrediccionDTO.getArticuloId());

                if (demandaHistoricaMes <= 0) {
                    int anio = fechaDesde.getYear();
                    int mes = fechaDesde.getMonthValue();

                    PrediccionDemanda prediccionMesAnterior = prediccionDemandaRepository.prediccionPorArticuloAndFechas(parametrosPrediccionDTO.getArticuloId(), anio, mes);
                    if (prediccionMesAnterior != null && prediccionMesAnterior.getValorPrediccion() != null) {
                        demandaHistoricaMes = prediccionMesAnterior.getValorPrediccion();
                    } else {
                        demandaHistoricaMes = 0;
                    }
                }

                sumaXY += (nroMes * demandaHistoricaMes);
                sumaX2 += Math.pow(nroMes, 2);
                sumaDemandas += demandaHistoricaMes;
                sumaPeriodos += nroMes;
            }
            double promedioPeriodos = (double) sumaPeriodos / cantidadPeriodosAUsar;
            double promedioDemandas = sumaDemandas / cantidadPeriodosAUsar;
            double promPeriodosCuadrado = Math.pow(promedioPeriodos, 2);

            b = (sumaXY - (cantidadPeriodosAUsar * promedioPeriodos * promedioDemandas)) / (sumaX2 - (cantidadPeriodosAUsar * promPeriodosCuadrado));
            a = promedioDemandas - (b * promedioPeriodos);

            Integer valorPrediccion = (int) (a + (b * (cantidadPeriodosAUsar + 1)));

            return valorPrediccion;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public List<PrediccionDemanda> crearPrediccion(ParametrosPrediccionDTO parametrosPrediccionDTO) throws Exception {
        try {
            Articulo articulo = articuloService.findById(parametrosPrediccionDTO.getArticuloId());
            List<PrediccionDemanda> listaPredicciones = new ArrayList<>();
            LocalDate fechaInicial = LocalDate.of(parametrosPrediccionDTO.getAnioPrediccion(), parametrosPrediccionDTO.getMesPrediccion(), 1);
            for (int i = 0; i < parametrosPrediccionDTO.getCantidadPeriodosAPredecir(); i++) {
                int valorPrediccion = 0;
                switch (parametrosPrediccionDTO.getMetodoPrediccion()) {
                    case PROMEDIO_MOVIL_PONDERADO:
                        valorPrediccion = calculoPMPonderado(parametrosPrediccionDTO);
                        break;
                    case PROMEDIO_MOVIL_SUAVIZADO:
                        valorPrediccion = calculoPMPSuavizado(parametrosPrediccionDTO);
                        break;
                    case REGRESION_LINEAL:
                        valorPrediccion = calculoRegresionLineal(parametrosPrediccionDTO);
                        break;
                    case ESTACIONALIDAD:
                        valorPrediccion = calculoPEstacional(parametrosPrediccionDTO);
                        break;
                }

                LocalDate fechaDesde = fechaInicial.plusMonths(i);
                PrediccionDemanda prediccion = new PrediccionDemanda();
                prediccion.setValorPrediccion(valorPrediccion);
                prediccion.setFechaPrediccion(fechaDesde);
                prediccion.setArticulo(articulo);
                prediccion.setMetodoPrediccion(parametrosPrediccionDTO.getMetodoPrediccion());
                prediccionDemandaRepository.save(prediccion);
                listaPredicciones.add(prediccion);
            }
            return listaPredicciones;

        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public List<PrediccionDemanda> crearPrediccionPredeterminada(ParametrosPrediccionDTO parametrosPrediccionDTO) throws Exception {
        try {
            Articulo articulo = articuloService.findById(parametrosPrediccionDTO.getArticuloId());
            List<PrediccionDemanda> listaPredicciones = new ArrayList<>();
            LocalDate fechaInicial = LocalDate.of(parametrosPrediccionDTO.getAnioPrediccion(), parametrosPrediccionDTO.getMesPrediccion(), 1);
            for (int i = 0; i < parametrosPrediccionDTO.getCantidadPeriodosAPredecir(); i++) {
                int valorPrediccion = 0;
                switch (articulo.getMetodoPred()) {
                    case PROMEDIO_MOVIL_PONDERADO:
                        valorPrediccion = calculoPMPonderado(parametrosPrediccionDTO);
                        break;
                    case PROMEDIO_MOVIL_SUAVIZADO:
                        valorPrediccion = calculoPMPSuavizado(parametrosPrediccionDTO);
                        break;
                    case REGRESION_LINEAL:
                        valorPrediccion = calculoRegresionLineal(parametrosPrediccionDTO);
                        break;
                    case ESTACIONALIDAD:
                        valorPrediccion = calculoPEstacional(parametrosPrediccionDTO);
                        break;
                }

                LocalDate fechaDesde = fechaInicial.plusMonths(i);
                PrediccionDemanda prediccion = new PrediccionDemanda();
                prediccion.setValorPrediccion(valorPrediccion);
                prediccion.setFechaPrediccion(fechaDesde);
                prediccion.setArticulo(articulo);
                prediccion.setMetodoPrediccion(parametrosPrediccionDTO.getMetodoPrediccion());
                prediccionDemandaRepository.save(prediccion);
                listaPredicciones.add(prediccion);
            }
            return listaPredicciones;

        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }



    @Override
    public void calculoError(ParametrosPrediccionDTO parametrosPrediccionDTO) throws Exception {
      int contador = 1;
            for(int k=0; k<contador; k++){
                calcularError(parametrosPrediccionDTO, k);
            }
    }


    @Override
    public void calcularError(ParametrosPrediccionDTO parametrosPrediccionDTO, int contador) throws Exception {
        try{
            Long idArticulo= parametrosPrediccionDTO.getArticuloId();
            Articulo articulo = articuloService.findById(idArticulo);
            int anioAPredecir= parametrosPrediccionDTO.getAnioPrediccion();
            int mesAPredecir=parametrosPrediccionDTO.getMesPrediccion();
            double prediccionPMP= calculoPMPonderado(parametrosPrediccionDTO);
            double prediccionPMSE= calculoPMPSuavizado(parametrosPrediccionDTO);
            double prediccionEST= calculoPEstacional(parametrosPrediccionDTO);

            //calculo del error
            LocalDate inicioPeriodo = LocalDate.of(anioAPredecir, mesAPredecir, 1);
            LocalDate finPeriodo = inicioPeriodo.withDayOfMonth(inicioPeriodo.lengthOfMonth());

            Date fechaDesdeDate = java.sql.Date.valueOf(inicioPeriodo);
            Date fechaHastaDate = java.sql.Date.valueOf(finPeriodo);

            int demandaReal = articuloService.demandaAnual(idArticulo);

            if(mesAPredecir <12) {
                mesAPredecir+= 1;
                parametrosPrediccionDTO.setMesPrediccion(mesAPredecir);
            } else {
                anioAPredecir+=1;
                mesAPredecir=1;
                parametrosPrediccionDTO.setMesPrediccion(mesAPredecir);
                parametrosPrediccionDTO.setAnioPrediccion(anioAPredecir);
            }

            if (demandaReal<=0){
                throw new Exception("No esta cargada la demanda necesaria para predecir ");
            }

            double errorPMP= prediccionPMP- demandaReal;
            double errorPMSE= prediccionPMSE- demandaReal;
            double errorEST= prediccionEST- demandaReal;

            //porcentaje de error
            double porcentajeDeErrorPMP = Math.abs(errorPMP / demandaReal) * 100;
            double porcentajeDeErrorPMSE = Math.abs(errorPMSE / demandaReal) * 100;
            double porcentajeDeErrorEST = Math.abs(errorEST / demandaReal) * 100;

            if(errorPMP<errorPMSE){
                if(errorPMP<errorEST){
                    parametrosPrediccionDTO.setError(errorPMP);
                    parametrosPrediccionDTO.setPorcentajeDeError(porcentajeDeErrorPMP);
                    parametrosPrediccionDTO.setPrediccion(prediccionPMP);
                    articulo.setMetodoPred(MetodoPrediccion.PROMEDIO_MOVIL_PONDERADO);
                    articuloRepository.save(articulo);
                } else {
                    parametrosPrediccionDTO.setError(errorEST);
                    parametrosPrediccionDTO.setPorcentajeDeError(porcentajeDeErrorEST);
                    parametrosPrediccionDTO.setPrediccion(prediccionEST);
                    articulo.setMetodoPred(MetodoPrediccion.PROMEDIO_MOVIL_SUAVIZADO);
                    articuloRepository.save(articulo);
                }
            } else {
                parametrosPrediccionDTO.setError(errorPMSE);
                parametrosPrediccionDTO.setPorcentajeDeError(porcentajeDeErrorPMSE);
                parametrosPrediccionDTO.setPrediccion(prediccionPMSE);
                articulo.setMetodoPred(MetodoPrediccion.ESTACIONALIDAD);
                articuloRepository.save(articulo);
            }


        }catch (Exception e){
            throw new Exception("Error al calcular la predicción de demanda: " + e.getMessage());
        }
    }

}
