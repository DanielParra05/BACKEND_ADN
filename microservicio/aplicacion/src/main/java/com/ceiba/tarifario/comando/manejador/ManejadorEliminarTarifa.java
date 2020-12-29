package com.ceiba.tarifario.comando.manejador;

import org.springframework.stereotype.Component;

import com.ceiba.manejador.ManejadorComando;
import com.ceiba.tarifario.servicio.ServicioEliminarTarifa;

@Component
public class ManejadorEliminarTarifa implements ManejadorComando<Long>{

	ServicioEliminarTarifa servicioEliminar;
	
	
	public ManejadorEliminarTarifa(ServicioEliminarTarifa servicioEliminar) {
		this.servicioEliminar = servicioEliminar;
	}
	
	@Override
	public void ejecutar(Long comando) {
		this.servicioEliminar.ejecutar(comando);
		
	}



}
