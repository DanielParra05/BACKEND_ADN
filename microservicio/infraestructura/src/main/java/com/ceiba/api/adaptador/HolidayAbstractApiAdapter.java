package com.ceiba.api.adaptador;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import com.ceiba.api.consumer.HolidayAbstractApiConsumer;
import com.ceiba.tiqueteparqueo.puerto.api.ApiValidadorFechaFestivo;

@Component
public class HolidayAbstractApiAdapter implements ApiValidadorFechaFestivo{

	@Override
	public boolean esFestivo(LocalDateTime fecha) {
		return HolidayAbstractApiConsumer.esFestivo(fecha);
	}
	
}
