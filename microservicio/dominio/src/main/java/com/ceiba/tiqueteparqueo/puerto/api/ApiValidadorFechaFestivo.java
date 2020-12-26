package com.ceiba.tiqueteparqueo.puerto.api;

import java.io.IOException;
import java.time.LocalDateTime;

public interface ApiValidadorFechaFestivo {

	boolean esFestivo(LocalDateTime fecha) throws IOException;

}
