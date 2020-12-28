package com.ceiba.infraestructura.servicio.externo.superfinancieratrm;

import java.util.Map;

import javax.xml.ws.BindingProvider;

import com.ceiba.infraestructura.servicio.externo.superfinancieratrm.libreria.TCRMServicesWebService;
import com.ceiba.infraestructura.servicio.externo.superfinancieratrm.libreria.TcrmResponse;
import com.ceiba.infraestructura.servicio.externo.superfinancieratrm.libreria.TcrmServicesInterface;
/**
 * Cliente para consumir el servicio SuperfinancieraWebServiceTRM
 * @author daniel.parra
 *
 */
public class ClienteSuperfinancieraWebServiceTrm {

	private static final String WSDL = "https://www.superfinanciera.gov.co/SuperfinancieraWebServiceTRM/TCRMServicesWebService/TCRMServicesWebService?WSDL";
	private static final String ENDPOINT_ADDRESS = "https://www.superfinanciera.gov.co/SuperfinancieraWebServiceTRM/TCRMServicesWebService/TCRMServicesWebService";
	
	/**
	 * Llamado a la operacion que obtiene la TRM
	 * @return Float valor de la TRM actual
	 */
	public static Float getTrm() {
		TcrmServicesInterface port = obtenerPort();
		TcrmResponse respuesta = port.queryTCRM("");

		return respuesta.getValue();
	}
	
	/**
	 * Creacion del puerto que posee las operaciones del servicio 
	 * @return TcrmServicesInterface port del web service
	 */
	private static TcrmServicesInterface obtenerPort() {
		TCRMServicesWebService service = new TCRMServicesWebService(
				ClienteSuperfinancieraWebServiceTrm.class.getClassLoader().getResource(WSDL));
		TcrmServicesInterface port = service.getTcrmServicesInterfaceImplPort();

		final BindingProvider bindProv = (BindingProvider) port;
		Map<String, Object> properties = bindProv.getRequestContext();
		properties.put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, ENDPOINT_ADDRESS);
		properties.put("http.keepAlive", false);
		properties.put("com.sun.xml.ws.connect.timeout", 30000);
		properties.put("com.sun.xml.ws.request.timeout", 30000);

		return port;
	}

}
