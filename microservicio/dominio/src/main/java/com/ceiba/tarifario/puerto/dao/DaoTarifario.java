package com.ceiba.tarifario.puerto.dao;

import java.util.List;
import java.util.Map;

import com.ceiba.tarifario.modelo.dto.DtoTarifa;

public interface DaoTarifario {
	/**
	 * Lista en un mapa las tarifas del parqueadero configuradas en bd
	 * @return map con key nombre de la tarifa y value valor de la tarifa
	 */
	 public Map<String, Double> listar();
	 
	 /**
	  * Listar con objetos
	  * @return
	  */
	 public List<DtoTarifa> listarComoObjetos();
	 
	 public DtoTarifa buscarPorId(Long id);
	
}
