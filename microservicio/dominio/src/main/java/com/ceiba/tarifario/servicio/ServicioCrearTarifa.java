package com.ceiba.tarifario.servicio;

import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import com.ceiba.tarifario.modelo.entidad.Tarifa;
import com.ceiba.tarifario.puerto.repositorio.RepositorioTarifario;

public class ServicioCrearTarifa {

	private final RepositorioTarifario repositorio;
	private final String TARIFA_DUPLICADA = "La tarifa ya existe";

	public ServicioCrearTarifa(RepositorioTarifario repositorio) {
		this.repositorio = repositorio;
	}

	public Long ejecutar(Tarifa tarifa) {
		validarDuplicidad(tarifa.getLlave());
		return repositorio.crear(tarifa);
	}

	public void validarDuplicidad(String llave) {
		boolean existe = this.repositorio.existePorLlave(llave);
		if (existe) {
			throw new ExcepcionDuplicidad(TARIFA_DUPLICADA);
		}

	}

}
