package com.ceiba.tiqueteparqueo.servicio;

import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import com.ceiba.tiqueteparqueo.modelo.entidad.TiqueteParqueo;
import com.ceiba.tiqueteparqueo.puerto.repositorio.RepositorioTiqueteParqueo;

public class ServicioActualizarTiqueteParqueo {

    private static final String EL_TIQUETE_YA_EXISTE_EN_EL_SISTEMA = "El vehiculo ya cuenta con un tiquete activo";

    private final RepositorioTiqueteParqueo repositorioTiqueteParqueo;

    public ServicioActualizarTiqueteParqueo(RepositorioTiqueteParqueo repositorioTiqueteParqueo) {
        this.repositorioTiqueteParqueo = repositorioTiqueteParqueo;
    }

    public void ejecutar(TiqueteParqueo tiqueteParqueo) {
        //validarExistenciaPrevia(tiqueteParqueo);
        this.repositorioTiqueteParqueo.actualizar(tiqueteParqueo);
    }
    
/**
    private void validarExistenciaPrevia(TiqueteParqueo tiqueteParqueo) {
        boolean existe = this.repositorioTiqueteParqueo.existeSinFechaSalida(tiqueteParqueo.getPlacaVehiculo());
        if(existe) {
            throw new ExcepcionDuplicidad(EL_TIQUETE_YA_EXISTE_EN_EL_SISTEMA);
        }
    }*/
}
