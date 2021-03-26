package com.cg.mts;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import com.cg.mts.entities.Cab;

@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
public class CabManagementTests extends AbstractTest {
	
	@Override
	@BeforeAll
	public void setUp() {
		super.setUp();
	}

	@Test
	public void createCab() throws Exception {
		String uri = "/cab";
		Cab cab = new Cab(222, "SuperXL", 450);
		String inputJson = super.mapToJson(cab);
		MvcResult mvcResult = mvc
				.perform(
						MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON).contentType(inputJson))
				.andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		Cab c = super.mapFromJson(content, Cab.class);
		assertEquals("SuperXL", c.getPerKmRate());

	}
	
	@Test
	public void getCabType() throws Exception{
		String uri = "/cab/type/SuperXL";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)).andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		Cab cabList[] = super.mapFromJson(content, Cab[].class);
		assertEquals("SuperXL", cabList[cabList.length-1].getCarType());
		
	}
	
	@Test
	public void deleteCab() throws Exception{
		this.mvc.perform(MockMvcRequestBuilders.delete("/cab/4").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	
	}
	
	@Test
	public void countCabsByType() throws Exception{
		String uri = "/cab/type/SUV";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)).andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		Cab cabList[] = super.mapFromJson(content, Cab[].class);
		assertEquals(3, cabList.length);
	}
	

	

}
