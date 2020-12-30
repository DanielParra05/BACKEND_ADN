package com.ceiba.tarifario.puerto.repositorio;

import com.ceiba.tarifario.modelo.entidad.Tarifa;

public interface RepositorioTarifario {
	/**
	 * Permite crear una tarifa
	 * 
	 * @param tiqueteParqueo
	 * @return el id generado
	 */
	Long crear(Tarifa tarifa);

	/**
	 * Permite actualizar una tarifa
	 * 
	 * @param tiqueteParqueo
	 */

	void actualizar(Tarifa tarifa);

	/**
	 * Permite eliminar una tarifa
	 * 
	 * @param id
	 */
	void eliminar(Long id);
	
	boolean existePorLlave(String llave);
}
