package com.ceiba.tiqueteparqueo.modelo;

public enum TipoVehiculo {
	CARRO("Carro"), MOTO("Moto");

	private String value;

	private TipoVehiculo(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

}
