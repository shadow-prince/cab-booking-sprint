package com.cg.mts.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.mts.entities.Admin;
import com.cg.mts.entities.TripBooking;
import com.cg.mts.exception.AdminNotFoundException;
import com.cg.mts.exception.CustomerNotFoundException;
import com.cg.mts.service.IAdminService;

@RestController
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	IAdminService ias;
	
	@GetMapping
	public List<Admin> viewALlAdmin(){
		return ias.viewALlAdmin();
	}
	
	@PostMapping
	public Admin insertAdmin(@RequestBody Admin admin) {
		return ias.insertAdmin(admin);
	}
	
	@DeleteMapping("/{adminId}")
	public Admin deleteAdmin(@PathVariable int adminId) throws AdminNotFoundException {
		return ias.deleteAdmin(adminId);
	}
	
	@PutMapping
	public Admin updateAdmin(@RequestBody Admin admin) throws AdminNotFoundException {
		return ias.updateAdmin(admin);
	}
	
	@GetMapping("/alltrips/{customerId}")
	public List<TripBooking> getAllTrips(@PathVariable int customerId) throws CustomerNotFoundException {
		return ias.getAllTrips(customerId);
	}

	@GetMapping("/cabwise")
	public List<TripBooking> getTripsCabwise() {
		return ias.getTripsCabwise();
	}

	@GetMapping("/customerwise")
	public List<TripBooking> getTripsCustomerwise() {
		return ias.getTripsCustomerwise();
	}

	@GetMapping("/datewise")
	public List<TripBooking> getTripsDatewise() {
		return ias.getTripsDatewise();
	}

	@GetMapping("fordays/{customerId}/{fromDate}/{toDate}")
	public List<TripBooking> getAllTripsForDays(@PathVariable("customerId") int customerId, @PathVariable("fromDate") LocalDateTime fromDate,@PathVariable("toDate") LocalDateTime toDate) throws CustomerNotFoundException {
		return ias.getAllTripsForDays(customerId, fromDate, toDate);
	}
	
}