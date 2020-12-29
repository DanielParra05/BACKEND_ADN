package com.ceiba.tarifario.comando;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ComandoTarifario {

	private Long id;
	private String llave;
	private Double valor;
}
