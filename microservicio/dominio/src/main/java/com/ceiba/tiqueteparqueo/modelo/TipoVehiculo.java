package com.ceiba.tiqueteparqueo.modelo;

import java.util.HashMap;
import java.util.Map;

public enum TipoVehiculo {
	CARRO("Carro"), MOTO("Moto");

	private String value;
	private static final Map<String, TipoVehiculo> map = new HashMap<>(values().length, 1);

	private TipoVehiculo(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public static TipoVehiculo of(String name) {
		for (TipoVehiculo tipoV : TipoVehiculo.values()) {
			if (tipoV.getValue().equals(name)) {
				return tipoV;
			}
		}
		throw new IllegalArgumentException("Tipo vehiculo no encontrado:"+name);
	}

}
