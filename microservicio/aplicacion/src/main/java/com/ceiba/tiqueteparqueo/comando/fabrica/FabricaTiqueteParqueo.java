package com.ceiba.tiqueteparqueo.comando.fabrica;

import com.ceiba.tiqueteparqueo.modelo.entidad.TiqueteParqueo;
import org.springframework.stereotype.Component;

import com.ceiba.tiqueteparqueo.comando.ComandoTiqueteParqueo;

@Component
public class FabricaTiqueteParqueo {

	public TiqueteParqueo crear(ComandoTiqueteParqueo comandoTiqueteParqueo) {
		return new TiqueteParqueo(comandoTiqueteParqueo.getId(), comandoTiqueteParqueo.getPlacaVehiculo(),
				comandoTiqueteParqueo.getTipoVehiculo(), comandoTiqueteParqueo.getFechaIngreso(), comandoTiqueteParqueo.getFechaSalida());
	}

}
