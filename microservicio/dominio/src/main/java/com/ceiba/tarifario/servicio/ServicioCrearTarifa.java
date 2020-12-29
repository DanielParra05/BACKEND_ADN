package com.ceiba.tarifario.servicio;

import com.ceiba.tarifario.modelo.entidad.Tarifa;
import com.ceiba.tarifario.puerto.repositorio.RepositorioTarifario;

public class ServicioCrearTarifa {

	private final RepositorioTarifario repositorio;

	public ServicioCrearTarifa(RepositorioTarifario repositorio) {
		this.repositorio = repositorio;
	}

	public Long ejecutar(Tarifa tarifa) {
		return repositorio.crear(tarifa);
	}

}
