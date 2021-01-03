package com.ceiba.tarifario.controlador;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.ceiba.ApplicationMock;
import com.ceiba.tarifario.comando.ComandoTarifario;
import com.ceiba.tarifario.servicio.testdatabuilder.ComandoTarifaTestDataBuilder;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes= ApplicationMock.class)
@WebMvcTest(ComandoControladorTarifario.class)
@AutoConfigureMockMvc(addFilters = false)
public class ComandoControladorTarifarioTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mocMvc;
    
    @Test
    public void crear() throws Exception{
    	//arrange
    	ComandoTarifario tarifario = new ComandoTarifaTestDataBuilder().build();
       
    	// act - assert
        mocMvc.perform(post("/tarifario")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(tarifario)))
                .andExpect(status().isOk())
                .andExpect(content().json("{'valor': 4}"));
    }
    
    @Test
    public void actualizar() throws Exception{
        // arrange
        Long id = 4L;
        ComandoTarifario tarifa = new ComandoTarifaTestDataBuilder().conValor(3500.0).build();

        // act - assert
        mocMvc.perform(put("/tarifario/{id}",id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(tarifa)))
                .andExpect(status().isOk());
    }
    
    @Test
    public void eliminar() throws Exception{
    	//arrange
    	Long id = 4L;
    	
        // act - assert
        mocMvc.perform(delete("/tarifario/{id}",id)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
