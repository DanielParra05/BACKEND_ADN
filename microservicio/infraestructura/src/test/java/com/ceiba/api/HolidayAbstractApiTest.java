package com.ceiba.api;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.ceiba.api.adaptador.HolidayAbstractApiAdapter;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=HolidayAbstractApiAdapter.class)
public class HolidayAbstractApiTest {

	@Autowired
	private HolidayAbstractApiAdapter abstractApi;
	
	@Test
	public void esFestivoTest() throws IOException, InterruptedException{
		TimeUnit.SECONDS.sleep(1); //Esperar 1 segundo para consumir de nuevo la API
		//arrange
		LocalDateTime festivoColombiano = LocalDateTime.of(2020, Month.JULY, 20, 13, 1);
		//act - assert
		Assert.assertTrue(abstractApi.esFestivo(festivoColombiano));
	}
	
	@Test
	public void noEsFestivoTest() throws IOException, InterruptedException {		
		TimeUnit.SECONDS.sleep(1); //Esperar 1 segundo para consumir de nuevo la API
		//arrange
		LocalDateTime festivoColombiano = LocalDateTime.of(2020, Month.JULY, 21, 13, 1);
		//act - assert
		Assert.assertFalse(abstractApi.esFestivo(festivoColombiano));
	}
}
