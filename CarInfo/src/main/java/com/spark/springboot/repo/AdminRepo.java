package com.spark.springboot.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spark.springboot.model.AdminInfo;

public interface AdminRepo extends JpaRepository<AdminInfo, Integer>{

	public AdminInfo findByAdminUserName(String adminUserName);

	public AdminInfo findByadminUserName(String adminUserName);

}
