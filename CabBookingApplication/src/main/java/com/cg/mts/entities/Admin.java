package com.cg.mts.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
public class Admin extends AbstractUser {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int adminId;

	
	  public Admin() {
	  
	  }
	 
	
	public int getAdminId() {
		return adminId;
	}

	public void setAdminId(int adminId) {
		this.adminId = adminId;
	}

	public Admin(int adminId) {
		super();
		this.adminId = adminId;
	}
}