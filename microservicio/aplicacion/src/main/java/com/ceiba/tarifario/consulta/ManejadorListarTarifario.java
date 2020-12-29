package com.ceiba.tarifario.consulta;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.ceiba.tarifario.puerto.dao.DaoTarifario;

@Component
public class ManejadorListarTarifario {

	private final DaoTarifario daoTarifario;
	
	public ManejadorListarTarifario (DaoTarifario daoTarifario) {
		this.daoTarifario = daoTarifario;		
	}
	
	public Map<String, Double> ejecutar() {		
		return daoTarifario.listar();
	}
	
}
