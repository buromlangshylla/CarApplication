package com.spark.springboot.model;

import org.springframework.stereotype.Component;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
@Component
@Entity
public class Car_Details {
	@Id
	@GeneratedValue(generator = "car_seq", strategy = GenerationType.AUTO)
	@SequenceGenerator(name = "car_seq", sequenceName = "car_sequence", initialValue = 1, allocationSize = 1)
	private int carId;
	private String carName;
	private String carCompany;
	private String fuel_Type;
	private boolean powerSteering;
	private String breakSystem;
	private double showroom_Price;
	private double onroad_Price;
	private double mileage;
	private int seating_Capacity;
	private int engine_Capacity;
	private String gear_Type;
	int price2;
	
	public int getPrice2() {
		return price2;
	}

	public void setPrice2(int price2) {
		this.price2 = price2;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	private boolean active;
	private String image_URL;
    int price;
	public boolean isActive() {
		return active;
	}

	public String getImage_URL() {
		return image_URL;
	}

	public void setImage_URL(String image_URL) {
		this.image_URL = image_URL;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public int getCarId() {
		return carId;
	}

	public void setCarId(int carId) {
		this.carId = carId;
	}

	public String getCarName() {
		return carName;
	}

	public void setCarName(String carName) {
		this.carName = carName;
	}

	public String getCarCompany() {
		return carCompany;
	}

	public void setCarCompany(String carCompany) {
		this.carCompany = carCompany;
	}

	public String getFuel_Type() {
		return fuel_Type;
	}

	public void setFuel_Type(String fuel_Type) {
		this.fuel_Type = fuel_Type;
	}

	public boolean isPowerSteering() {
		return powerSteering;
	}

	public void setPowerSteering(boolean powerSteering) {
		this.powerSteering = powerSteering;
	}

	public String getBreakSystem() {
		return breakSystem;
	}

	public void setBreakSystem(String breakSystem) {
		this.breakSystem = breakSystem;
	}

	public double getShowroom_Price() {
		return showroom_Price;
	}

	public void setShowroom_Price(double showroom_Price) {
		this.showroom_Price = showroom_Price;
	}

	public double getOnroad_Price() {
		return onroad_Price;
	}

	public void setOnroad_Price(double onroad_Price) {
		this.onroad_Price = onroad_Price;
	}

	public double getMileage() {
		return mileage;
	}

	public void setMileage(double mileage) {
		this.mileage = mileage;
	}

	public int getSeating_Capacity() {
		return seating_Capacity;
	}

	public void setSeating_Capacity(int seating_Capacity) {
		this.seating_Capacity = seating_Capacity;
	}

	public int getEngine_Capacity() {
		return engine_Capacity;
	}

	public void setEngine_Capacity(int engine_Capacity) {
		this.engine_Capacity = engine_Capacity;
	}

	public String getGear_Type() {
		return gear_Type;
	}

	public void setGear_Type(String gear_Type) {
		this.gear_Type = gear_Type;
	}

	@Override
	public String toString() {
		return carId + " " + carName + " " + carCompany + " " + fuel_Type + " " + powerSteering + " " + breakSystem
				+ " " + showroom_Price + " " + onroad_Price + " " + mileage + " " + seating_Capacity + " "
				+ engine_Capacity + " " + gear_Type + " " + active + " " + image_URL;
	}

}
