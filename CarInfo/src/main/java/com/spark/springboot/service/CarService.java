package com.spark.springboot.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.spark.springboot.model.Car_Details;

@Service
public interface CarService {

	public List<Car_Details> searchService(String query);
	public String loginService(String adminUserName, String password);
//	public String insertService(Car_Details carDetails);
	public String deleteService(int carId);
    public Double findOnRoadPrice(Double showroom_Price,String fuel_Type);
//	public String updateService(int carId, String carDetails, String carDetails2);
	public Car_Details upTestlogic(String id);
	String insertNew(String carName, String carCompany, String fuel_Type, String powerSteering, String breakSystem,
			String showroom_Price, String mileage, String seating_Capacity, String engine_Capacity, String gear_Type,
			String image_URL);
	public String updateNew(String carName, String carCompany, String fuel_Type, String pow, String breakSystem,
			String showroom_Price, String mileage, String seating_Capacity, String engine_Capacity, String gear_Type,
			String image_URL, String image_URL2);
	public String forgotPwdService(String adminUserName);
	public boolean changePwdService(String userId, String password, String confirm);

}
