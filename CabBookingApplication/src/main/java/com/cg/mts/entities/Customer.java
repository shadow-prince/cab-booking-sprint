package com.cg.mts.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class Customer extends AbstractUser {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int customerId;

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	
	public Customer() {
		
	}

	public Customer(int customerId) {
		super();
		this.customerId = customerId;
	}
	
}
