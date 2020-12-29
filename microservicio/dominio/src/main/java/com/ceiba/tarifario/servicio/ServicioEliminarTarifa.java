package com.ceiba.tarifario.servicio;

import com.ceiba.tarifario.puerto.repositorio.RepositorioTarifario;

public class ServicioEliminarTarifa {

	private final RepositorioTarifario repositorio;

	public ServicioEliminarTarifa(RepositorioTarifario repositorio) {
		this.repositorio = repositorio;
	}
	
	public void ejecutar (Long id) {
		this.repositorio.eliminar(id);		
	}
	
	
}
