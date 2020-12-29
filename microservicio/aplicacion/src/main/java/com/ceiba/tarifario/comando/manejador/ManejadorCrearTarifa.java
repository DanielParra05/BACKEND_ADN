package com.ceiba.tarifario.comando.manejador;

import org.springframework.stereotype.Component;

import com.ceiba.ComandoRespuesta;
import com.ceiba.manejador.ManejadorComandoRespuesta;
import com.ceiba.tarifario.comando.ComandoTarifario;
import com.ceiba.tarifario.comando.fabrica.FabricaTarifario;
import com.ceiba.tarifario.modelo.entidad.Tarifa;
import com.ceiba.tarifario.servicio.ServicioCrearTarifa;

@Component
public class ManejadorCrearTarifa implements ManejadorComandoRespuesta<ComandoTarifario, ComandoRespuesta<Long>> {
	
	private final FabricaTarifario factory;
	private final ServicioCrearTarifa servicioCrear;

	
	public ManejadorCrearTarifa(FabricaTarifario factory, ServicioCrearTarifa servicioCrear) {
		this.factory = factory;
		this.servicioCrear = servicioCrear;
	}

	@Override
	public ComandoRespuesta<Long> ejecutar(ComandoTarifario comandoTarifario) {
		Tarifa tarifa = this.factory.crear(comandoTarifario);
		return new ComandoRespuesta<>(this.servicioCrear.ejecutar(tarifa));
	}
	
	

}
