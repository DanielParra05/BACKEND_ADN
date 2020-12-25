package com.ceiba.api.consumer;

import java.io.IOException;
import java.time.LocalDateTime;

import org.apache.http.client.fluent.Content;
import org.apache.http.client.fluent.Request;

/**
 * Consumidor de holidays.abstractapi.com para verificar si una fecha es festivo
 * en el calendario colombiano
 * 
 * @author daniel.parra
 *
 */
public class HolidayAbstractApiConsumer {

	private static final String PAIS = "CO";
	private static final String API_KEY = "5e0e2b64c9964607bac699cc84e42f74";
	private static final String END_POINT = "https://holidays.abstractapi.com/v1/?";

	public static boolean esFestivo(LocalDateTime fecha) {
		try {
			Content content = Request.Get(END_POINT + "api_key=+" + API_KEY + "&country=" + PAIS + "&year="
					+ fecha.getYear() + "&month=" + fecha.getMonthValue() + "&day=" + fecha.getDayOfMonth()).execute()
					.returnContent();

			return !"[]".equals(content.toString());
		} catch (IOException error) {
			// throw some error
			return false;
		}
	}
}
