package com.ceiba.api.adaptador;

import java.time.LocalDateTime;

import com.ceiba.api.consumer.HolidayAbstractApiConsumer;
import com.ceiba.tiqueteparqueo.puerto.api.HolidayAbstractApiAdapter;

public class HolidayAbstractApiPort implements HolidayAbstractApiAdapter{

	@Override
	public boolean esFestivo(LocalDateTime fecha) {
		return HolidayAbstractApiConsumer.esFestivo(fecha);
	}
	
}
