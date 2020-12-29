package com.ceiba.configuracion;

import com.ceiba.tarifario.puerto.dao.DaoTarifario;
import com.ceiba.tarifario.puerto.repositorio.RepositorioTarifario;
import com.ceiba.tarifario.servicio.ServicioActualizarTarifa;
import com.ceiba.tarifario.servicio.ServicioCrearTarifa;
import com.ceiba.tarifario.servicio.ServicioEliminarTarifa;
import com.ceiba.tiqueteparqueo.puerto.api.ApiValidadorFechaFestivo;
import com.ceiba.tiqueteparqueo.puerto.repositorio.RepositorioTiqueteParqueo;
import com.ceiba.tiqueteparqueo.servicio.ServicioActualizarTiqueteParqueo;
import com.ceiba.tiqueteparqueo.servicio.ServicioCrearTiqueteParqueo;
import com.ceiba.tiqueteparqueo.servicio.ServicioEliminarTiqueteParqueo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanServicio {

	// ---- Beans servicio para Tiquete parqueo ---
	@Bean
	public ServicioCrearTiqueteParqueo servicioCrearTiqueteParqueo(
			RepositorioTiqueteParqueo repositorioTiqueteParqueo) {
		return new ServicioCrearTiqueteParqueo(repositorioTiqueteParqueo);
	}

	@Bean
	public ServicioEliminarTiqueteParqueo servicioEliminarTiqueteParqueo(
			RepositorioTiqueteParqueo repositorioTiqueteParqueo) {
		return new ServicioEliminarTiqueteParqueo(repositorioTiqueteParqueo);
	}

	@Bean
	public ServicioActualizarTiqueteParqueo servicioActualizarTiqueteParqueo(
			RepositorioTiqueteParqueo repositorioTiqueteParqueo, ApiValidadorFechaFestivo validadorFechaFestivo,
			DaoTarifario daoTarifario) {
		return new ServicioActualizarTiqueteParqueo(repositorioTiqueteParqueo, validadorFechaFestivo, daoTarifario);
	}

	// ---- Beans servicio para Tarifario ---
	@Bean
	public ServicioCrearTarifa servicioCrearTarifa(RepositorioTarifario repositorio) {
		return new ServicioCrearTarifa(repositorio);
	}

	@Bean
	public ServicioActualizarTarifa servicioActualizarTarifa(RepositorioTarifario repositorio) {
		return new ServicioActualizarTarifa(repositorio);
	}

	@Bean
	public ServicioEliminarTarifa servicioEliminarTarifa(RepositorioTarifario repositorio) {
		return new ServicioEliminarTarifa(repositorio);
	}

}
