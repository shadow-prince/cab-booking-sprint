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

import com.cg.mts.entities.Admin;

@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
public class AdminManagementTests extends AbstractTest {
 
	@Override
	@BeforeAll
	public void setUp() {
		super.setUp();
	}

	
	@Test
	public void getAdminUsername() throws Exception {
		String uri = "/admin/2";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)).andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		Admin admin = super.mapFromJson(content, Admin.class);
		assertEquals("admin1", admin.getUsername());
	}

	
	@Test
	public void getStausCode() throws Exception {
		String uri = "/admin";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)).andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
	}

	
	@Test
	public void createAdmin() throws Exception {
		String uri = "/admin";
		Admin admin = new Admin();
		admin.setEmail("creator@cab.in");
		admin.setMobileNumber("756324189");
		admin.setPassword("password");
		admin.setUsername("createadmin");

		String inputJson = super.mapToJson(admin);
		MvcResult mvcResult = mvc.perform(
				MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		Admin a = super.mapFromJson(content, Admin.class);
		assertEquals("creator@cab.in", a.getEmail());

	}
	

	@Test
	public void updateAdmin() throws Exception {
		String uri = "/admin/2";
		String uriPost = "/admin";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)).andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		Admin admin = super.mapFromJson(content, Admin.class);
		admin.setEmail("updatedEmail.com");
		String inputJson = super.mapToJson(admin);
		MvcResult mvcResultt = mvc.perform(
				MockMvcRequestBuilders.post(uriPost).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
				.andReturn();
		int postStatus = mvcResultt.getResponse().getStatus();
		assertEquals(200, postStatus);
		String responseContent = mvcResultt.getResponse().getContentAsString();
		Admin a = super.mapFromJson(responseContent, Admin.class);
		assertEquals("updatedEmail.com", a.getEmail());

	}

	
	@Test
	public void deleteAdmin() throws Exception {

		this.mvc.perform(MockMvcRequestBuilders.delete("/admin/4").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}

}
