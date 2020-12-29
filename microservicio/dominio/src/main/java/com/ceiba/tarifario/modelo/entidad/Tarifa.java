package com.ceiba.tarifario.modelo.entidad;

import lombok.Getter;

@Getter
public class Tarifa {

	Long id;
	String llave;
	Double valor;
	
	public Tarifa(Long id, String llave, Double valor) {
		this.id = id;
		this.llave = llave;
		this.valor = valor;
	}
}
