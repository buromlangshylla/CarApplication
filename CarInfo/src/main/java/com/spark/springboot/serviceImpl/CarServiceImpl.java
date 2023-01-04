package com.spark.springboot.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.jasypt.encryption.StringEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.spark.springboot.jasyptConfig.EncConfig;
import com.spark.springboot.model.AdminInfo;
import com.spark.springboot.model.Car_Details;
import com.spark.springboot.repo.AdminRepo;
import com.spark.springboot.repo.CarRepo;
import com.spark.springboot.service.CarService;

@Service
public class CarServiceImpl implements CarService {
	@Autowired
	CarRepo carRepo;
	@Autowired
	AdminRepo adminRepo;
	@Autowired
	Car_Details car;
	@Autowired
	EncConfig config;
	@Autowired
	JavaMailSender mailSender;
	@Value("${spring.mail.username}")
	private String sender;

	@Override
	public List<Car_Details> searchService(String query) {
		
		List<Car_Details> list = carRepo.searchCar(query);
		List<Car_Details> list2 = new ArrayList<Car_Details>();

		for (Car_Details obj : list) {
			if (obj.isActive()) {
				list2.add(obj);
			}
		}
		return list2;
	}


	@Override
	public String loginService(String adminUserName, String password) {

		AdminInfo admin = adminRepo.findByAdminUserName(adminUserName);
		if (admin == null) {
			return "not Verified";
		}
		String pwd = admin.getPassword();
		StringEncryptor passwordEncryptor = config.getPasswordEncryptor();
		String decrypt = passwordEncryptor.decrypt(pwd);
		if (decrypt.equals(password))
		{
			return "verified";
		}
		    return "not Verified";
	}

	@Override
	public String insertNew(String carName, String carCompany, String fuel_Type, String pow, String breakSystem,
			String showroom_Price, String mileage, String seating_Capacity, String engine_Capacity, String gear_Type,
			String image_URL) {
		double showroomPrice = 0.0;
		double onRoadPrice = 0.0;
		car.setActive(true);
		car.setCarName(carName);
		car.setCarCompany(carCompany);
		car.setFuel_Type(fuel_Type);
		car.setBreakSystem(breakSystem);
		car.setPowerSteering(Boolean.parseBoolean(pow));
		try {
			showroomPrice = Double.parseDouble(showroom_Price);
			onRoadPrice = findOnRoadPrice(showroomPrice, fuel_Type);
			car.setShowroom_Price(showroomPrice);
			car.setOnroad_Price(onRoadPrice + showroomPrice);
			car.setMileage(Double.parseDouble(mileage));
			car.setSeating_Capacity(Integer.parseInt(seating_Capacity));
			car.setEngine_Capacity(Integer.parseInt(engine_Capacity));
		} catch (Exception e) {
			return "couldn't insert data due to field mismatch";
		}
		car.setGear_Type(gear_Type);
		car.setImage_URL(image_URL);
		car.setPrice((int) showroomPrice);
		car.setPrice2((int) onRoadPrice + (int) showroomPrice);
		carRepo.save(car);
		return "inserted successfully";
	}

	@Override
	public String deleteService(int carId) {
		Car_Details findByCarId = carRepo.findByCarId(carId);
		if (findByCarId == null) {
			return "data not found";
		}
		boolean active = findByCarId.isActive();
		if (active) {
			findByCarId.setActive(!active);
			carRepo.save(findByCarId);
			return "data has been removed";
		}
		return "data not found";
	}

	@Override
	public Double findOnRoadPrice(Double showroom_Price, String fuel_Type) {
		if (fuel_Type.equals("electric")) {
			return ((4 * showroom_Price) / 100);
		}
		else if (showroom_Price < 500000) {
			return ((13 * showroom_Price) / 100);
		} else if (showroom_Price >= 500000 && showroom_Price < 1000000) {
			return ((14 * showroom_Price) / 100);
		}
		else if (showroom_Price >= 1000000 && showroom_Price < 2000000) {
			return ((17 * showroom_Price) / 100);
		} else {
			return ((18 * showroom_Price) / 100);
		}
	}

	@Override
	public Car_Details upTestlogic(String id) {
		Car_Details findById = carRepo.findBycarId(Integer.parseInt(id));
		if (findById == null) {
			return null;
		}
		return findById;
	}

	@Override
	public String updateNew(String carId, String carName, String carCompany, String fuel_Type, String pow,
			String breakSystem, String showroom_Price, String mileage, String seating_Capacity, String engine_Capacity,
			String gear_Type, String image_URL) {
		
		double onRoadPrice;
		double showroomPrice;
		
		Car_Details findByCarId = carRepo.findByCarId(Integer.parseInt(carId));

		if (findByCarId == null) {
			return "data not found or has been removed";
		}
		if (!findByCarId.isActive()) {
			return "data not found or has been removed";
		}
		try {
			showroomPrice = Double.parseDouble(showroom_Price);
			onRoadPrice = findOnRoadPrice(showroomPrice, fuel_Type);
		} catch (Exception e) {
			return "couldn't insert data due to field mismatch";
		}
		findByCarId.setBreakSystem(breakSystem);
		findByCarId.setCarCompany(carCompany);
		findByCarId.setCarName(carName);
		findByCarId.setEngine_Capacity(Integer.parseInt(engine_Capacity));
		findByCarId.setFuel_Type(fuel_Type);
		findByCarId.setGear_Type(fuel_Type);
		findByCarId.setImage_URL(image_URL);
		findByCarId.setMileage(Double.parseDouble(mileage));
		findByCarId.setPowerSteering(Boolean.parseBoolean(pow));
		findByCarId.setSeating_Capacity(Integer.parseInt(seating_Capacity));
		findByCarId.setShowroom_Price(showroomPrice);
		findByCarId.setOnroad_Price(onRoadPrice + showroomPrice);
		findByCarId.setPrice((int) showroomPrice);
		findByCarId.setPrice2((int) onRoadPrice + (int) showroomPrice);
		carRepo.save(findByCarId);
		return "data has been updated";
	}

	@Override
	public String forgotPwdService(String adminUserName) {
	AdminInfo admin = adminRepo.findByadminUserName(adminUserName);
		if(admin==null) {
			return "please enter your user Id";
		}
		String emailId = admin.getEmail();
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom(sender);
		message.setTo(emailId);
		message.setText("http://localhost:8080/pwdchange");
		message.setSubject("password change");
		mailSender.send(message);
		return "reset link has been emailed to your Id";
	}

	@Override
	public boolean changePwdService(String adminUserName, String password, String confirm) {
		AdminInfo admin = adminRepo.findByadminUserName(adminUserName);
		if (password.equals(confirm)) {
			StringEncryptor passwordEncryptor = config.getPasswordEncryptor();
			String decrypt = passwordEncryptor.encrypt(password);
			admin.setPassword(decrypt);
			adminRepo.save(admin);
			return true;
		}
		return false;
	}
	}
