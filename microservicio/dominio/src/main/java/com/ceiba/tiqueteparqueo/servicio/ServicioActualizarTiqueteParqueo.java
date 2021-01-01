package com.ceiba.tiqueteparqueo.servicio;

import java.io.IOException;
import java.time.DayOfWeek;
import java.util.Map;

import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import com.ceiba.dominio.excepcion.ExcepcionSinDatos;
import com.ceiba.tarifario.puerto.dao.DaoTarifario;
import com.ceiba.tiqueteparqueo.modelo.entidad.TiqueteParqueo;
import com.ceiba.tiqueteparqueo.puerto.api.ApiValidadorFechaFestivo;
import com.ceiba.tiqueteparqueo.puerto.repositorio.RepositorioTiqueteParqueo;

public class ServicioActualizarTiqueteParqueo {

	private final RepositorioTiqueteParqueo repositorioTiqueteParqueo;
	private final ApiValidadorFechaFestivo validadorFechaFestivo;
	private final DaoTarifario daoTarifario;
	private static final String EL_SERVICIO_DE_VALIDACION_FESTIVOS_FALLO = "La API de validacion de festivos ha fallado: ";
	private static final String EL_TIQUETE_NO_EXISTE_EN_EL_SISTEMA = "El tiquete a actualizar no existe en el sistema";

	private static final double VEINTE_POR_CIENTO = 0.20;
	private static final double CINCUENTA_POR_CIENTO = 0.50;
	private static final String KEY_RECARGO_NOCTURNO = "Recargo_Nocturno";
	private static final int ONCE_PM = 23;
	private static final int CINCO_AM = 5;

	public ServicioActualizarTiqueteParqueo(RepositorioTiqueteParqueo repositorioTiqueteParqueo,
			ApiValidadorFechaFestivo validadorFechaFestivo, DaoTarifario daoTarifario) {
		this.repositorioTiqueteParqueo = repositorioTiqueteParqueo;
		this.validadorFechaFestivo = validadorFechaFestivo;
		this.daoTarifario = daoTarifario;
	}

	public void ejecutar(TiqueteParqueo tiqueteParqueo) {
		validarExistenciaPrevia(tiqueteParqueo.getId());
		try {
			this.asignarValorPagar(daoTarifario.listar(), tiqueteParqueo);
			this.repositorioTiqueteParqueo.actualizar(tiqueteParqueo);
		} catch (IOException e) {
			throw new ExcepcionSinDatos(EL_SERVICIO_DE_VALIDACION_FESTIVOS_FALLO + e.getMessage(), e);
		}
	}

	private void validarExistenciaPrevia(Long id) {
		boolean existe = repositorioTiqueteParqueo.existePorId(id);
		if (!existe) {
			throw new ExcepcionDuplicidad(EL_TIQUETE_NO_EXISTE_EN_EL_SISTEMA);
		}
	}

	/**
	 * Asigna el valor a pagar del tiquete basado en las reglas de negocio
	 * 
	 * @throws IOException si falla la API de validacion de festivos
	 */
	public void asignarValorPagar(Map<String, Double> tarifario, TiqueteParqueo tiqueteParqueo) throws IOException {
		if (tiqueteParqueo.getFechaSalida() != null && tiqueteParqueo.getValorAPagar() == 0.0) {
			tiqueteParqueo.calcularTarifaNormal(tarifario.get(tiqueteParqueo.getTipoVehiculo()));

			if (tiqueteParqueo.getFechaSalida().getDayOfWeek() != DayOfWeek.SATURDAY
					&& tiqueteParqueo.getFechaSalida().getDayOfWeek() != DayOfWeek.SUNDAY
					&& (tiqueteParqueo.getFechaSalida().getHour() >= ONCE_PM
							|| tiqueteParqueo.getFechaSalida().getHour() <= CINCO_AM)) {
				tiqueteParqueo.aplicarAdicionAlPago((tarifario.get(KEY_RECARGO_NOCTURNO)));
			}

			if (tiqueteParqueo.getFechaIngreso().getDayOfWeek().compareTo(DayOfWeek.SUNDAY) == 0
					|| tiqueteParqueo.getFechaIngreso().getDayOfWeek().compareTo(DayOfWeek.SATURDAY) == 0) {
				tiqueteParqueo.aplicarAdicionPorcentualAlPago(VEINTE_POR_CIENTO);
			} else if (validadorFechaFestivo.esFestivo(tiqueteParqueo.getFechaIngreso())) {
				tiqueteParqueo.aplicarDescuentoPorcentualAlPago(CINCUENTA_POR_CIENTO);
			}
		}
	}

}
