package com.ceiba.tarifario.servicio.testdatabuilder;

import com.ceiba.tarifario.modelo.entidad.Tarifa;

public class TarifarioTestDataBuilder {

	private Long id;
	private String llave;
	private Double valor;
	
	public TarifarioTestDataBuilder() {
		llave="Test";
		valor= 3500.0;
	}
	
    public TarifarioTestDataBuilder conLlave(String llave) {
        this.llave = llave;
        return this;
    }
    
    public TarifarioTestDataBuilder conId(Long id) {
        this.id = id;
        return this;
    }
    
    public TarifarioTestDataBuilder conValor(Double valor) {
        this.valor = valor;
        return this;
    }
	
    public Tarifa build() {
    	return new Tarifa(id, llave, valor);
    }
	
    
}
