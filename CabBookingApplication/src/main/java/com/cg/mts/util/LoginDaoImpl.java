package com.cg.mts.util;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.cg.mts.entities.Admin;
import com.cg.mts.entities.Customer;
import com.cg.mts.entities.Driver;
@Transactional
@Repository
public class LoginDaoImpl implements LoginDao {

	@PersistenceContext
	EntityManager em;
	@Override
	public String validateCredintials(Object obj) {
          if(obj instanceof Customer) {
        	  Customer c= (Customer) obj;
        	  TypedQuery<Customer> q=em.createQuery("select c from Customer c where c.username=:uname and c.password=:pass",Customer.class);
        	  q.setParameter("uname", c.getUsername());
        	  q.setParameter("pass", c.getPassword());
        	  
        	  Customer cust=q.getSingleResult();
        	  if(cust!=null)
        		  return "success";
        	  else 
        		  return "Invalid";
          }
          else if(obj instanceof Driver) {
        	  Driver d= (Driver) obj;
        	  TypedQuery<Driver> q=em.createQuery("select dr from Driver dr where dr.username=:uname and dr.password=:pass",Driver.class);
        	  q.setParameter("uname", d.getUsername());
        	  q.setParameter("pass", d.getPassword());
        	  
        	  Driver driver=q.getSingleResult();
        	  if(driver!=null)
        		  return "success";
        	  else 
        		  return "Invalid";
          }
          else if(obj instanceof Admin) {
        	  Admin a= (Admin) obj;
        	  TypedQuery<Admin> q=em.createQuery("select ad from Admin ad where ad.username=:uname and ad.password=:pass",Admin.class);
        	  q.setParameter("uname", a.getUsername());
        	  q.setParameter("pass", a.getPassword());
        	  
        	  Admin admin=q.getSingleResult();
        	  if(admin!=null)
        		  return "success";
        	  else 
        		  return "Invalid";
          }
          return null;
	}

}
