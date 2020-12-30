package com.ceiba.tarifario.modelo.entidad;

import static com.ceiba.dominio.ValidadorArgumento.validarObligatorio;

import lombok.Getter;

@Getter
public class Tarifa {
	
	private static final String SE_DEBE_INGRESAR_LLAVE = "Se debe Ingresar la llave de la tarifa";
	private static final String SE_DEBE_INGRESAR_VALOR = "Se debe Ingresar el valor de la tarifa";
	
	private Long id;
	private String llave;
	private Double valor;
	
	public Tarifa(Long id, String llave, Double valor) {		
		validarObligatorio(llave, SE_DEBE_INGRESAR_LLAVE);
		validarObligatorio(valor, SE_DEBE_INGRESAR_VALOR);
		this.id = id;
		this.llave = llave;
		this.valor = valor;
	}
}
