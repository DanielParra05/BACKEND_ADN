package com.ceiba.tiqueteparqueo.servicio;

import com.ceiba.tiqueteparqueo.puerto.repositorio.RepositorioTiqueteParqueo;

public class ServicioEliminarTiqueteParqueo {

    private final RepositorioTiqueteParqueo repositorioTiqueteParqueo;

    public ServicioEliminarTiqueteParqueo(RepositorioTiqueteParqueo repositorioTiqueteParqueo) {
        this.repositorioTiqueteParqueo = repositorioTiqueteParqueo;
    }

    public void ejecutar(Long id) {
        this.repositorioTiqueteParqueo.eliminar(id);
    }
}
