package com.ceiba.tiqueteparqueo.comando.manejador;

import com.ceiba.manejador.ManejadorComando;
import com.ceiba.tiqueteparqueo.servicio.ServicioEliminarTiqueteParqueo;
import org.springframework.stereotype.Component;


@Component
public class ManejadorEliminarTiqueteParqueo implements ManejadorComando<Long> {

    private final ServicioEliminarTiqueteParqueo servicioEliminarTiqueteParqueo;

    public ManejadorEliminarTiqueteParqueo(ServicioEliminarTiqueteParqueo servicioEliminarTiqueteParqueo) {
        this.servicioEliminarTiqueteParqueo = servicioEliminarTiqueteParqueo;
    }

    public void ejecutar(Long idTiqueteParqueo) {
        this.servicioEliminarTiqueteParqueo.ejecutar(idTiqueteParqueo);
    }
}
