package com.ceiba.tiqueteparqueo.comando.manejador;

import com.ceiba.manejador.ManejadorComando;
import com.ceiba.tiqueteparqueo.modelo.entidad.TiqueteParqueo;
import com.ceiba.tiqueteparqueo.servicio.ServicioActualizarTiqueteParqueo;
import org.springframework.stereotype.Component;

import com.ceiba.tiqueteparqueo.comando.ComandoTiqueteParqueo;
import com.ceiba.tiqueteparqueo.comando.fabrica.FabricaTiqueteParqueo;

@Component
public class ManejadorActualizarTiqueteParqueo implements ManejadorComando<ComandoTiqueteParqueo> {

    private final FabricaTiqueteParqueo fabricaTiqueteParqueo;
    private final ServicioActualizarTiqueteParqueo servicioActualizarTiqueteParqueo;

    public ManejadorActualizarTiqueteParqueo(FabricaTiqueteParqueo fabricaTiqueteParqueo, ServicioActualizarTiqueteParqueo servicioActualizarTiqueteParqueo) {
        this.fabricaTiqueteParqueo = fabricaTiqueteParqueo;
        this.servicioActualizarTiqueteParqueo = servicioActualizarTiqueteParqueo;
    }

    public void ejecutar(ComandoTiqueteParqueo comandoTiqueteParqueo) {
        TiqueteParqueo tiqueteParqueo = this.fabricaTiqueteParqueo.crear(comandoTiqueteParqueo);
        this.servicioActualizarTiqueteParqueo.ejecutar(tiqueteParqueo);
    }
}
