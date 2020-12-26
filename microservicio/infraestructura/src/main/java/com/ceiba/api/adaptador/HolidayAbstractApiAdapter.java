package com.ceiba.api.adaptador;

import java.io.IOException;
import java.time.LocalDateTime;

import org.apache.http.client.fluent.Content;
import org.apache.http.client.fluent.Request;
import org.springframework.stereotype.Component;

import com.ceiba.tiqueteparqueo.puerto.api.ApiValidadorFechaFestivo;

@Component
public class HolidayAbstractApiAdapter implements ApiValidadorFechaFestivo{

	private static final String PAIS = "CO";
	private static final String API_KEY = "5e0e2b64c9964607bac699cc84e42f74";
	private static final String END_POINT = "https://holidays.abstractapi.com/v1/?";
	
	@Override
	public boolean esFestivo(LocalDateTime fecha) throws IOException {
		Content content = Request.Get(END_POINT + "api_key=+" + API_KEY + "&country=" + PAIS + "&year="
				+ fecha.getYear() + "&month=" + fecha.getMonthValue() + "&day=" + fecha.getDayOfMonth()).execute()
				.returnContent();

		return !"[]".equals(content.toString());
	}
	
}
