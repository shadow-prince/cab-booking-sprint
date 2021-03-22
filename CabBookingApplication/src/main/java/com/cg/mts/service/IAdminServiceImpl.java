package com.cg.mts.service;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.mts.entities.Admin;
import com.cg.mts.entities.TripBooking;
import com.cg.mts.exception.AdminNotFoundException;
import com.cg.mts.exception.CustomerNotFoundException;
import com.cg.mts.repository.IAdminRepository;


@Service("ias")
public class IAdminServiceImpl implements IAdminService  {

	@Autowired
	IAdminRepository iar;
	
	@Override
	public Admin insertAdmin(Admin admin) {
		iar.insertAdmin(admin);
		return null;
	}

	@Override
	public Admin updateAdmin(Admin admin) throws AdminNotFoundException {
		return iar.updateAdmin(admin);
	}

	@Override
	public Admin deleteAdmin(int adminId) throws AdminNotFoundException {
		return iar.deleteAdmin(adminId);
	}

	@Override
	public List<TripBooking> getAllTrips(int customerId) throws CustomerNotFoundException {
		return iar.getAllTrips(customerId);
	}

	@Override
	public List<TripBooking> getTripsCabwise() {
		return iar.getTripsCabwise();
	}

	@Override
	public List<TripBooking> getTripsCustomerwise() {
		return iar.getTripsCustomerwise();
	}

	@Override
	public List<TripBooking> getTripsDatewise() {
		return iar.getTripsDatewise();
	}

	@Override
	public List<TripBooking> getAllTripsForDays(int customerId, LocalDateTime fromDate, LocalDateTime toDate) throws CustomerNotFoundException {
		return iar.getAllTripsForDays(customerId, fromDate, toDate);
	}

	@Override
	public List<Admin> viewALlAdmin() {
		return iar.viewAllAdmin();
	}

}