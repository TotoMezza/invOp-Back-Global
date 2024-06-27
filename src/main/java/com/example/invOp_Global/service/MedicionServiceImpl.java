package com.example.invOp_Global.service;

import com.example.invOp_Global.dtos.ParametrosPrediccionDTO;
import com.example.invOp_Global.entities.DetalleOrdenCompra;
import com.example.invOp_Global.entities.MedicionError;
import com.example.invOp_Global.repository.BaseRepository;
import com.example.invOp_Global.repository.MedicionErrorRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class MedicionServiceImpl extends BaseServiceImpl<MedicionError,Long> implements  MedicionErrorService{
    @Autowired
    private MedicionErrorRepository medicionErrorRepository;
    @Autowired
    private ArticuloService articuloService;
    @Autowired
    private PrediccionDemandaService prediccionDemandaService;
    @Autowired
    private  DemandaService demandaService;

    public MedicionServiceImpl(BaseRepository<MedicionError, Long> baseRepository) {
        super(baseRepository);
        this.medicionErrorRepository = medicionErrorRepository;
        this.articuloService = articuloService;
        this.demandaService = demandaService;
        this.prediccionDemandaService = prediccionDemandaService;
    }
    public Double calculoError(ParametrosPrediccionDTO datosError) throws Exception{
            double sumatoriaErrorPMP = 0.0;
            double sumatoriaErrorPMSE = 0.0;
            double sumatoriaErrorRL = 0.0;
            double sumatoriaErrorEst = 0.0;
            List<Integer> listaDemandasHistoricas = crearListaHistoricasParaError(12, datosError.getFechaDesde(), datosError.getIdArticulo());
            int denominador = 0;
            long cantidadMeses = ChronoUnit.MONTHS.between(datosError.getFechaDesde(), datosError.getFechaHasta());
            for(int i = 0; i < cantidadMeses+1; i++) {

                LocalDate fechaInicioMes = datosError.getFechaDesde().plusMonths(i).withDayOfMonth(1);
                LocalDate fechaFinMes = fechaInicioMes.withDayOfMonth(fechaInicioMes.lengthOfMonth());
                datosError.setMesPrediccion(datosError.getFechaDesde().plusMonths(i).getMonthValue());
                datosError.setAnioPrediccion(datosError.getAnioPrediccion());
                int pronosticoDemandaPMP = 0;
                int pronosticoDemandaPMSE = 0;
                int pronosticoDemandaRL = 0;
                int pronosticoDemandaEst = 0;

                pronosticoDemandaPMP = prediccionDemandaService.calculoPMPonderado(datosError);
                pronosticoDemandaPMSE = prediccionDemandaService.calculoPMPSuavizado(datosError);
                pronosticoDemandaRL = prediccionDemandaService.calculoRegresionLineal(datosError);
                pronosticoDemandaEst = prediccionDemandaService.calculoPEstacional(datosError);
            }
            int demandaReal = listaDemandasHistoricas.get(i);
            if (demandaReal > 0) {
                    double errorAbsolutoPMP = ((double) Math.abs(demandaReal - pronosticoDemandaPMP)) / demandaReal;
                    double errorAbsolutoPMSE = ((double) Math.abs(demandaReal - pronosticoDemanda)) / demandaReal;
                    double errorAbsolutoRL = ((double) Math.abs(demandaReal - pronosticoDemanda)) / demandaReal;
                    double errorAbsolutoEst = ((double) Math.abs(demandaReal - pronosticoDemanda)) / demandaReal;

                    sumatoriaError += errorAbsoluto;
                    denominador += 1;
            }

            double errorPorcentual = 100 * (sumatoriaError)/denominador;

            return errorPorcentual;
        }

    public List<Integer> crearListaHistoricasParaError(int cantPeriodos, LocalDate fechaDesde, Long idArticulo) throws Exception {
        try{
            List<Integer> listaDemandasHistoricas = new ArrayList<>();
            for (int i = 0; i < cantPeriodos; i++) {
                LocalDate fechaInicioMes = fechaDesde.plusMonths(i).withDayOfMonth(1);
                LocalDate fechaFinMes = fechaInicioMes.withDayOfMonth(fechaInicioMes.lengthOfMonth());
                int demandaReal = demandaService.calcularDemanda(fechaInicioMes, fechaFinMes, idArticulo);
                if (demandaReal < 0){
                    demandaReal = 0;
                }
                listaDemandasHistoricas.add(demandaReal);
            }
            return listaDemandasHistoricas;
        }catch(Exception e){
            throw new Exception(e.getMessage());
        }
    }
}

