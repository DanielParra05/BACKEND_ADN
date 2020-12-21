package com.ceiba.configuracion;

import com.ceiba.tiqueteparqueo.puerto.repositorio.RepositorioTiqueteParqueo;
import com.ceiba.tiqueteparqueo.servicio.ServicioActualizarTiqueteParqueo;
import com.ceiba.tiqueteparqueo.servicio.ServicioCrearTiqueteParqueo;
import com.ceiba.tiqueteparqueo.servicio.ServicioEliminarTiqueteParqueo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanServicio {

    @Bean
    public ServicioCrearTiqueteParqueo servicioCrearTiqueteParqueo(RepositorioTiqueteParqueo repositorioTiqueteParqueo) {
        return new ServicioCrearTiqueteParqueo(repositorioTiqueteParqueo);
    }

    @Bean
    public ServicioEliminarTiqueteParqueo servicioEliminarTiqueteParqueo(RepositorioTiqueteParqueo repositorioTiqueteParqueo) {
        return new ServicioEliminarTiqueteParqueo(repositorioTiqueteParqueo);
    }

    @Bean
    public ServicioActualizarTiqueteParqueo servicioActualizarTiqueteParqueo(RepositorioTiqueteParqueo repositorioTiqueteParqueo) {
        return new ServicioActualizarTiqueteParqueo(repositorioTiqueteParqueo);
    }
	

}
