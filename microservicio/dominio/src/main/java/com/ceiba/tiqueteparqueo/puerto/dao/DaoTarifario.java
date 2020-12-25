package com.ceiba.tiqueteparqueo.puerto.dao;

import java.util.Map;

public interface DaoTarifario {
	/**
	 * Lista en un mapa las tarifas del parqueadero configuradas en bd
	 * @return map con key nombre de la tarifa y value valor de la tarifa
	 */
	 public Map<String, Double> listar();
	
}
