package com.cg.mts.service;

import java.time.LocalDateTime;
import java.util.List;

import com.cg.mts.entities.Admin;
import com.cg.mts.entities.TripBooking;
import com.cg.mts.exception.AdminNotFoundException;
import com.cg.mts.exception.CustomerNotFoundException;

public interface IAdminService {
	public Admin insertAdmin(Admin admin);
	public Admin updateAdmin(Admin admin) throws AdminNotFoundException;
	public Admin deleteAdmin(int adminId) throws AdminNotFoundException;
	public List<TripBooking>getAllTrips(int customerId) throws CustomerNotFoundException;
	public List<TripBooking>getTripsCabwise();
	public List<TripBooking>getTripsCustomerwise();
	public List<TripBooking>getTripsDatewise();
	public List<TripBooking>getAllTripsForDays(int customerId, LocalDateTime fromDate, LocalDateTime toDate) throws CustomerNotFoundException;
	public List<Admin> viewALlAdmin();
}