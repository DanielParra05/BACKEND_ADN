package com.ceiba.tiqueteparqueo.comando;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ComandoTiqueteParqueo {

	private Long id;
	private String placaVehiculo;
	private String tipoVehiculo;
	private LocalDateTime fechaIngreso;
	private LocalDateTime fechaSalida;
	private Double valorAPagar;

}
