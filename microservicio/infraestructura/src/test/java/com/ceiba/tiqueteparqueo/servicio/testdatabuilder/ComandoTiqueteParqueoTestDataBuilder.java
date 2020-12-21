package com.ceiba.tiqueteparqueo.servicio.testdatabuilder;

import com.ceiba.tiqueteparqueo.comando.ComandoTiqueteParqueo;

import java.time.LocalDateTime;
import java.util.UUID;

public class ComandoTiqueteParqueoTestDataBuilder {

	private Long id;
	private String placaVehiculo;
	private String tipoVehiculo;
	private LocalDateTime fechaIngreso;
	private LocalDateTime fechaSalida;
	private Double valorAPagar;

    public ComandoTiqueteParqueoTestDataBuilder() {
    	placaVehiculo = UUID.randomUUID().toString();
    	tipoVehiculo = "Moto";
    	fechaIngreso = LocalDateTime.now();
    }

    public ComandoTiqueteParqueoTestDataBuilder conPlacaVehiculo(String placaVehiculo) {
        this.placaVehiculo = placaVehiculo;
        return this;
    }

    public ComandoTiqueteParqueo build() {
        return new ComandoTiqueteParqueo(id, placaVehiculo, tipoVehiculo, fechaIngreso,fechaSalida, valorAPagar);
    }
}
