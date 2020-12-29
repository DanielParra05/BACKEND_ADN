package com.ceiba.tarifario.comando.manejador;

import org.springframework.stereotype.Component;

import com.ceiba.manejador.ManejadorComando;
import com.ceiba.tarifario.comando.ComandoTarifario;
import com.ceiba.tarifario.comando.fabrica.FabricaTarifario;
import com.ceiba.tarifario.modelo.entidad.Tarifa;
import com.ceiba.tarifario.servicio.ServicioActualizarTarifa;

@Component
public class ManejadorActualizarTarifa  implements ManejadorComando<ComandoTarifario> {

	private final FabricaTarifario fabricaTarifario;
	private final ServicioActualizarTarifa servicioActualizarTarifa;	
		
	
	public ManejadorActualizarTarifa(FabricaTarifario fabricaTarifario,
			ServicioActualizarTarifa servicioActualizarTarifa) {
		this.fabricaTarifario = fabricaTarifario;
		this.servicioActualizarTarifa = servicioActualizarTarifa;
	}

	@Override
	public void ejecutar(ComandoTarifario comando) {
		Tarifa tarifa = this.fabricaTarifario.crear(comando);
		this.servicioActualizarTarifa.ejecutar(tarifa);		
	}
	
	

}
