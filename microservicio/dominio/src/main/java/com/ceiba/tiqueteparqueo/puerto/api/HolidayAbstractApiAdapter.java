package com.ceiba.tiqueteparqueo.puerto.api;

import java.time.LocalDateTime;

public interface HolidayAbstractApiAdapter {

	boolean esFestivo(LocalDateTime fecha);

}
