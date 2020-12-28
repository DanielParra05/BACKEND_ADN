package com.ceiba.servicio.externo;

import java.io.IOException;
import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import com.ceiba.infraestructura.servicio.externo.holidayapi.ClienteHolidayAbstractApi;
import com.ceiba.tiqueteparqueo.puerto.api.ApiValidadorFechaFestivo;

@Component
public class ApiValidadorFechaFestivoAdapter implements ApiValidadorFechaFestivo{

	@Override
	public boolean esFestivo(LocalDateTime fecha) throws IOException {
		return ClienteHolidayAbstractApi.esFestivo(fecha);
	}

}
