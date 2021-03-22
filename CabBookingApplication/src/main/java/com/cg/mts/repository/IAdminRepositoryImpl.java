package com.cg.mts.repository;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.cg.mts.entities.Admin;
import com.cg.mts.entities.TripBooking;
import com.cg.mts.exception.AdminNotFoundException;
import com.cg.mts.exception.CustomerNotFoundException;

@Repository("iar")
@Transactional
public class IAdminRepositoryImpl implements IAdminRepository {

	@PersistenceContext
	EntityManager em;
	
	@Override
	public Admin insertAdmin(Admin admin) {
		em.persist(admin);
		return null;
	}

	@Override
	public Admin updateAdmin(Admin admin) throws AdminNotFoundException {
		Admin updateAdmin = em.find(Admin.class, admin.getAdminId());
		updateAdmin.setAdminId(admin.getAdminId());
		em.merge(updateAdmin);
		return updateAdmin;
	}

	@Override
	public Admin deleteAdmin(int adminId) throws AdminNotFoundException {
		Admin deleteAdmin = em.find(Admin.class, adminId);
		em.remove(deleteAdmin);
		return null;
	}

	@Override
	public List<TripBooking> getAllTrips(int customerId) throws CustomerNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TripBooking> getTripsCabwise() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TripBooking> getTripsCustomerwise() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TripBooking> getTripsDatewise() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TripBooking> getAllTripsForDays(int customerId, LocalDateTime fromDate, LocalDateTime toDate)
			throws CustomerNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Admin> viewAllAdmin() {
		Query q = em.createNativeQuery("select * from admin",Admin.class);
		return q.getResultList();
	
	}

	
}