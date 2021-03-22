package com.cg.mts.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Admin extends AbstractUser {
	@Id
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