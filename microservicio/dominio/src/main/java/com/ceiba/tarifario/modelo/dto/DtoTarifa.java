package com.ceiba.tarifario.modelo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DtoTarifa {
	private Long id;
	private String llave;
	private Double valor;
}
