package com.ceiba.tarifario.consulta;

import org.springframework.stereotype.Component;

import com.ceiba.tarifario.modelo.dto.DtoTarifa;
import com.ceiba.tarifario.puerto.dao.DaoTarifario;

@Component
public class ManejadorBuscarTarifaPorId {

	private final DaoTarifario daoTarifario;
	
	public ManejadorBuscarTarifaPorId (DaoTarifario daoTarifario) {
		this.daoTarifario = daoTarifario;		
	}
	
	public DtoTarifa ejecutar(Long id) {		
		return daoTarifario.buscarPorId(id);
	}
	
}
