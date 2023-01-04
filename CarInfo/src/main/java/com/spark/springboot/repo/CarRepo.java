package com.spark.springboot.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.spark.springboot.model.Car_Details;

public interface CarRepo extends JpaRepository<Car_Details, Integer> {
	@Query("SELECT c FROM Car_Details c WHERE " + "c.carName LIKE CONCAT('%',:query, '%')"
			+ "Or c.carCompany LIKE CONCAT('%', :query, '%')" + "Or c.fuel_Type LIKE CONCAT('%', :query, '%')")
	List<Car_Details> searchCar(String query);
	public Car_Details findByCarId(int carId);
	public List<Car_Details> searchByCarName(String name);
    public Car_Details findBycarId(int parseInt);

	
}
