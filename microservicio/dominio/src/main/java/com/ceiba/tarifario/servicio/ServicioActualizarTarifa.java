package com.ceiba.tarifario.servicio;

import com.ceiba.tarifario.modelo.entidad.Tarifa;
import com.ceiba.tarifario.puerto.repositorio.RepositorioTarifario;

public class ServicioActualizarTarifa {

	private final RepositorioTarifario repositorio;

	public ServicioActualizarTarifa(RepositorioTarifario repositorio) {
		this.repositorio = repositorio;
	}
	
	public void ejecutar (Tarifa tarifa) {
	this.repositorio.actualizar(tarifa);			
	}
	
	
}
