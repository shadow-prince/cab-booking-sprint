
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
import org.springframework.transaction.annotation.Transactional;

import com.cg.mts.entities.Cab;
import com.cg.mts.entities.Driver;
import com.fasterxml.jackson.core.JsonProcessingException;
 
@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
public class DriverManagementTest extends AbstractTest {

	@Override

	@BeforeAll
	public void setUp() {
		super.setUp();
	}	
	/**
	 * displayAllDriversTest
	 * @throws Exception
	 */
	@Test
	public void displayAllDriversTest() throws Exception {
		String uri = "/driver";
		Cab c = new Cab(3,"Macro", 8);
		Cab c1 = new Cab(4,"Nano",5);
		Driver d = new Driver(202,"license",c,4.8f);
		d.setUsername("ganesh");
		Driver d1 = new Driver(203,"license",c1,4.8f);
		d1.setUsername("kumar");
		String inputJson = super.mapToJson(d);
		MvcResult mvcResult = mvc.perform(
				MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
				.andReturn();
		String inputJson1 = super.mapToJson(d1);
		MvcResult mvcResult1 = mvc.perform(
				MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson1))
				.andReturn();
		String content = mvcResult.getResponse().getContentAsString();
		Driver driver[] = super.mapFromJson(content, Driver[].class);
		assertEquals("ganesh", driver[driver.length-1].getUsername());
		String content1 = mvcResult1.getResponse().getContentAsString();
		Driver driver1[] = super.mapFromJson(content1, Driver[].class);
		assertEquals("kumar", driver1[driver1.length-1].getUsername());
		
	}
	
    /**
     * displayAllDriversTest2
     * @throws Exception
     */
	@Test
	public void displayAllDriversTest2() throws Exception {
		String uri = "/driver";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)).andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
	}
	/**
	 * viewDriverByIdTestCase
	 * @throws Exception
	 */
	@Test
	public void viewDriverByIdTestCase() throws Exception {
		String uri = "/driver/9";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)).andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		Driver driver = super.mapFromJson(content, Driver.class);
		assertEquals(9, driver.getDriverId());
		
	}

	@Test
	public void bestDriversTestCase() throws Exception {
		String uri = "/driver/bestdrivers";
		MvcResult mvcResult = mvc.perform(
				MockMvcRequestBuilders.get(uri))
				.andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		Driver driver[] = super.mapFromJson(content, Driver[].class);
		boolean flag = true;
		for (Driver d : driver) {
			if(d.getRating()<4.5) {
				flag=false;
			}
		assertEquals(true, flag);
		}
	}
	
    /**
     * insertDriverTestCase
     * @throws Exception
     */
	@Test
	public void insertDriverTestCase() throws Exception {

		String uri = "/driver";
		Cab c = new Cab(3,"Macro", 8);
		Cab c1 = new Cab(4,"Nano",5);
		Driver d = new Driver(202,"license",c,4.8f);
		d.setUsername("hello");
		String inputJson = super.mapToJson(d);
		MvcResult mvcResult = mvc.perform(
				MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
				.andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		Driver driver[] = super.mapFromJson(content, Driver[].class);
		assertEquals("hello", driver[driver.length-1].getUsername());

	}
    /**
     * updateDriver
     * @throws Exception
     */
	@Test
	public void updateDriver() throws Exception {
		String uri = "/driver/6";
		String uriPost = "/driver";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)).andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		Driver driver = super.mapFromJson(content, Driver.class);
		driver.setEmail("updatedEmail.com");
		String inputJson = super.mapToJson(driver);
		MvcResult mvcResultt = mvc.perform(
				MockMvcRequestBuilders.put(uriPost).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
				.andReturn();
		int postStatus = mvcResultt.getResponse().getStatus();
		assertEquals(200, postStatus);
		String responseContent = mvcResultt.getResponse().getContentAsString();
		Driver d = super.mapFromJson(responseContent, Driver.class);
		assertEquals("updatedEmail.com", d.getEmail());

	}
    /**
     * deleteDriver
     * @throws Exception
     */
	@Test
	public void deleteDriver() throws Exception {
		this.mvc.perform(MockMvcRequestBuilders.delete("/driver/49").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}

}
