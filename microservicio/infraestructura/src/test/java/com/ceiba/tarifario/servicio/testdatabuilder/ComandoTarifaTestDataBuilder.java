package com.ceiba.tarifario.servicio.testdatabuilder;

import com.ceiba.tarifario.comando.ComandoTarifario;

public class ComandoTarifaTestDataBuilder {

	private Long id;
	private String llave;
	private Double valor;
	
	public ComandoTarifaTestDataBuilder() {
		this.llave = "Test";
		this.valor = 3500.0;
	}
	
	public ComandoTarifaTestDataBuilder conValor(Double valor) {
		this.valor = 3500.0;
		return this;
	}
	
	public ComandoTarifario build() {
		return new ComandoTarifario(this.id, this.llave, this.valor);
	}
	
	
}
