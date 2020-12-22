package com.ceiba.tiqueteparqueo.modelo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class DtoTiqueteParqueo {
	private Long id;
	private String placaVehiculo;
	private String tipoVehiculo;
	private LocalDateTime fechaIngreso;
	private LocalDateTime fechaSalida;
	private Double valorAPagar;
}
