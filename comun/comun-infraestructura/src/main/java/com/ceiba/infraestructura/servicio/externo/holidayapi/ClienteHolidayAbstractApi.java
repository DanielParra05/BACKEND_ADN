package com.ceiba.infraestructura.servicio.externo.holidayapi;

import java.io.IOException;
import java.time.LocalDateTime;

import org.apache.http.client.fluent.Content;
import org.apache.http.client.fluent.Request;

/**
 * Cliente para consumir la api de https://holidays.abstractapi.com para
 * consultar festivos en Colombia
 * 
 * @author daniel.parra
 *
 */
public class ClienteHolidayAbstractApi {

	private static final String PAIS = "CO";
	private static final String API_KEY = "5e0e2b64c9964607bac699cc84e42f74";
	private static final String END_POINT = "https://holidays.abstractapi.com/v1/?";
	
	/**
	 * Llamado al servicio de holidays.abstractapi.com
	 * @param fecha fecha a evaluar
	 * @return true si la fecha es festivo de lo contrario false
	 * @throws IOException
	 */
	public static boolean esFestivo(LocalDateTime fecha) throws IOException {
		Content content = Request.Get(END_POINT + "api_key=+" + API_KEY + "&country=" + PAIS + "&year="
				+ fecha.getYear() + "&month=" + fecha.getMonthValue() + "&day=" + fecha.getDayOfMonth()).execute()
				.returnContent();

		return !"[]".equals(content.toString());
	}

}
