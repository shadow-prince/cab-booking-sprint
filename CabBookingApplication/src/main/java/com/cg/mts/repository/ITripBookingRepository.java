package com.cg.mts.repository;

import java.util.List;

import com.cg.mts.entities.TripBooking;

public interface ITripBookingRepository {
	public TripBooking insertTripBooking(TripBooking tripBooking);
	public TripBooking updateTripBooking(TripBooking tripBooking);
	public TripBooking deleteTripBooking(TripBooking tripBooking);
	public List<TripBooking> viewAllTripsCustomer(int customerId);
	public TripBooking calculateBill(int customerId);
}




