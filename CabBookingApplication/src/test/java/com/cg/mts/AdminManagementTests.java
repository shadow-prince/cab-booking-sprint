
package com.cg.mts;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.cg.mts.entities.Admin;
import com.cg.mts.entities.TripBooking;

@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
public class AdminManagementTests extends AbstractTest {

	@Override
	@BeforeAll
	public void setUp() {
		super.setUp();
	}
    /**
     * viewAllAdmin
     * @throws Exception
     */
	@Test
	public void viewAllAdmin() throws Exception {
		String uri = "/admin";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)).andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		Admin admin[] = super.mapFromJson(content, Admin[].class);
		assertEquals(66, admin[0].getAdminId());
	}
    /**
     * createAdmin
     * @throws Exception
     */
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
    /**
     * updateAdmin
     * @throws Exception
     */
	@Test
	public void updateAdmin() throws Exception {
		String uri = "/admin/71";
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
    /**
     * deleteAdmin  
     * @throws Exception
     */
	@Test
	public void deleteAdmin() throws Exception {

		this.mvc.perform(MockMvcRequestBuilders.delete("/admin/89").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}
    /**
     * getAdminById
     * @throws Exception
     */
	@Test
	public void getAdminById() throws Exception {
		String uri = "/admin/66";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)).andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		Admin admin = super.mapFromJson(content, Admin.class);
		assertEquals("createadmin", admin.getUsername());
	}
    /**
     * showAllTrips
     * @throws Exception
     */
	@Test
	public void showAllTrips() throws Exception {
		String uri = "/admin/alltrips/62";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)).andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		TripBooking tripList[] = super.mapFromJson(content, TripBooking[].class);
		assertEquals(62, tripList[0].getCustomer().getCustomerId());

	}
    /**
     * showTripsCabWise
     * @throws Exception
     */
	@Test
	public void showTripsCabWise() throws Exception {
		String uri = "/admin/cabwise";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)).andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		TripBooking tripList[] = super.mapFromJson(content, TripBooking[].class);
		assertTrue(tripList[0].getDriver().getDriverId() <= tripList[1].getDriver().getDriverId());
	}
    /**
     * showTripsCustomerWise
     * @throws Exception
     */
	@Test
	public void showTripsCustomerWise() throws Exception {
		String uri = "/admin/customerwise";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)).andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		TripBooking tripList[] = super.mapFromJson(content, TripBooking[].class);
		assertTrue(tripList[0].getCustomer().getCustomerId() <= tripList[1].getCustomer().getCustomerId());
	}
    /**
     * showTripsDateWise
     * @throws Exception
     */
	@Test
	public void showTripsDateWise() throws Exception {
		boolean compare = false;
		String uri = "/admin/datewise";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)).andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		TripBooking tripList[] = super.mapFromJson(content, TripBooking[].class);
		boolean isBefore = tripList[0].getToDateTime().isBefore(tripList[1].getFromDateTime());
		boolean isEqual = tripList[0].getToDateTime().isEqual(tripList[1].getFromDateTime());
		if (isBefore || isEqual) {
			compare = true;
		}
		assertEquals(true, compare);
	}
    /**
     * showTripsForDates
     * @throws Exception
     */
	@Test
	public void showTripsForDates() throws Exception {
		String uri = "/admin/fordays/62/2021-03-04T04:50:26.838Z/2021-04-04T04:50:26.838Z";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)).andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		TripBooking tripList[] = super.mapFromJson(content, TripBooking[].class);
		assertEquals(62, tripList[0].getCustomer().getCustomerId());
		assertEquals(true, tripList[0].getFromDateTime().isAfter(LocalDateTime.parse("2021-03-04T04:50:26.838")));
		assertEquals(true, tripList[0].getToDateTime().isBefore(LocalDateTime.parse("2021-04-04T04:50:26.838")));
	}

}
