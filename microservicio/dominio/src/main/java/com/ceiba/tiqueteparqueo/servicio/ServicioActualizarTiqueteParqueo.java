package com.ceiba.tiqueteparqueo.servicio;

import com.ceiba.tiqueteparqueo.modelo.entidad.TiqueteParqueo;
import com.ceiba.tiqueteparqueo.puerto.repositorio.RepositorioTiqueteParqueo;

public class ServicioActualizarTiqueteParqueo {

    private final RepositorioTiqueteParqueo repositorioTiqueteParqueo;

    public ServicioActualizarTiqueteParqueo(RepositorioTiqueteParqueo repositorioTiqueteParqueo) {
        this.repositorioTiqueteParqueo = repositorioTiqueteParqueo;
    }

    public void ejecutar(TiqueteParqueo tiqueteParqueo) {
        this.repositorioTiqueteParqueo.actualizar(tiqueteParqueo);
    }
    
}
