package com.ceiba.tarifario.comando.fabrica;

import org.springframework.stereotype.Component;

import com.ceiba.tarifario.comando.ComandoTarifario;
import com.ceiba.tarifario.modelo.entidad.Tarifa;

@Component
public class FabricaTarifario {
	
	public Tarifa crear (ComandoTarifario comandoTarifario) {
		
		return new Tarifa(comandoTarifario.getId(), comandoTarifario.getLlave(), comandoTarifario.getValor());
		
	}

}
