package com.ceiba.tiqueteparqueo.servicio.testdatabuilder;

import com.ceiba.tiqueteparqueo.modelo.TipoVehiculo;
import com.ceiba.tiqueteparqueo.modelo.entidad.TiqueteParqueo;

import java.time.LocalDateTime;

public class TiqueteParqueoTestDataBuilder {

	private Long id;
	private String placaVehiculo;
	private TipoVehiculo tipoVehiculo;
	private LocalDateTime fechaIngreso;
	private LocalDateTime fechaSalida;
	private Double valorAPagar;

    public TiqueteParqueoTestDataBuilder() {
    	placaVehiculo = "GKK550";
    	tipoVehiculo = TipoVehiculo.CARRO;
    	fechaIngreso = LocalDateTime.now();
    }

    public TiqueteParqueoTestDataBuilder conPlacaVehiculo(String placaVehiculo) {
        this.placaVehiculo = placaVehiculo;
        return this;
    }

    public TiqueteParqueoTestDataBuilder conId(Long id) {
        this.id = id;
        return this;
    }
    
    public TiqueteParqueoTestDataBuilder conPlaca(String placa) {
        this.placaVehiculo = placa;
        return this;
    }

    public TiqueteParqueo build() {
        return new TiqueteParqueo(id, placaVehiculo, tipoVehiculo.toString(), fechaIngreso, fechaSalida, valorAPagar);
    }
}
