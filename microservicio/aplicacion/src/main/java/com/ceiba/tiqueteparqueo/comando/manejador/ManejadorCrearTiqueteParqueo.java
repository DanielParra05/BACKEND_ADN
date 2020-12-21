package com.ceiba.tiqueteparqueo.comando.manejador;

import com.ceiba.ComandoRespuesta;
import com.ceiba.manejador.ManejadorComandoRespuesta;
import com.ceiba.tiqueteparqueo.modelo.entidad.TiqueteParqueo;
import com.ceiba.tiqueteparqueo.servicio.ServicioCrearTiqueteParqueo;
import org.springframework.stereotype.Component;

import com.ceiba.tiqueteparqueo.comando.ComandoTiqueteParqueo;
import com.ceiba.tiqueteparqueo.comando.fabrica.FabricaTiqueteParqueo;

@Component
public class ManejadorCrearTiqueteParqueo implements ManejadorComandoRespuesta<ComandoTiqueteParqueo, ComandoRespuesta<Long>> {

    private final FabricaTiqueteParqueo fabricaTiqueteParqueo;
    private final ServicioCrearTiqueteParqueo servicioCrearTiqueteParqueo;

    public ManejadorCrearTiqueteParqueo(FabricaTiqueteParqueo fabricaTiqueteParqueo, ServicioCrearTiqueteParqueo servicioCrearTiqueteParqueo) {
        this.fabricaTiqueteParqueo = fabricaTiqueteParqueo;
        this.servicioCrearTiqueteParqueo = servicioCrearTiqueteParqueo;
    }

    public ComandoRespuesta<Long> ejecutar(ComandoTiqueteParqueo comandoTiqueteParqueo) {
        TiqueteParqueo TiqueteParqueo = this.fabricaTiqueteParqueo.crear(comandoTiqueteParqueo);
        return new ComandoRespuesta<>(this.servicioCrearTiqueteParqueo.ejecutar(TiqueteParqueo));
    }
}
