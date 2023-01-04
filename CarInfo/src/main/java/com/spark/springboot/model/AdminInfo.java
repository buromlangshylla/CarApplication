package com.spark.springboot.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class AdminInfo {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int adminId;
	private String adminUserName;
	private String password;
	private String email;
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getAdminId() {
		return adminId;
	}

	public void setAdminId(int adminId) {
		this.adminId = adminId;
	}

	public String getAdminUserName() {
		return adminUserName;
	}

	public void setAdminUserName(String adminUserName) {
		this.adminUserName = adminUserName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
