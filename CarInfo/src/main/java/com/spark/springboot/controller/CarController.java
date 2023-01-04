package com.spark.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.spark.springboot.model.Car_Details;
import com.spark.springboot.service.CarService;

@Controller
public class CarController {
	@Autowired
	CarService carService;
	String carId;
	String adminUserName;
	@GetMapping("/searchCar")
	public ModelAndView searchControl(@RequestParam("carName") String carName) {
		List<Car_Details> list = carService.searchService(carName);
		ModelAndView model = new ModelAndView();
		model.setViewName("search.html");
		if (list.isEmpty()) {
	    model.addObject("Notfound", "NO macthing found!!");
		} else {
		model.addObject("carList", list);
		}
		return model;
	}

	@ResponseBody
	@PostMapping("/logout")
	public ModelAndView loginControl(@RequestParam("adminUserName") String adminUserName,
			@RequestParam("password") String password) {
		String service = carService.loginService(adminUserName, password);
		ModelAndView model = new ModelAndView();
		if (service == "verified") {
			model.setViewName("afterlogin.html");
		} else {
			model.setViewName("form.html");
			model.addObject("loginStatus", service);
		}
		return model;
	}

	@ResponseBody
	@PostMapping("/insert")
	public ModelAndView insertControl(@RequestParam("carName") String carName,
			@RequestParam("carCompany") String carCompany, @RequestParam("fuel_Type") String fuel_Type,
			@RequestParam("powerSteering") String pow, @RequestParam("breakSystem") String breakSystem,
			@RequestParam("showroom_Price") String showroom_Price, @RequestParam("mileage") String mileage,
			@RequestParam("seating_Capacity") String seating_Capacity,
			@RequestParam("engine_Capacity") String engine_Capacity, @RequestParam("gear_Type") String gear_Type,
			@RequestParam("image_URL") String image_URL) {
		String service = carService.insertNew(carName, carCompany, fuel_Type, pow, breakSystem, showroom_Price, mileage,
		seating_Capacity, engine_Capacity, gear_Type, image_URL);
		ModelAndView model = new ModelAndView();
		model.setViewName("insert.html");
		model.addObject("inserted", service);
		return model;
	}

	@ResponseBody
	@PostMapping("/updatating")
	public ModelAndView updateControl(@RequestParam("carId") String carId, @RequestParam("carName") String carName,
			@RequestParam("carCompany") String carCompany, @RequestParam("fuel_Type") String fuel_Type,
			@RequestParam("powerSteering") String pow, @RequestParam("breakSystem") String breakSystem,
			@RequestParam("showroom_Price") String showroom_Price, @RequestParam("mileage") String mileage,
			@RequestParam("seating_Capacity") String seating_Capacity,
			@RequestParam("engine_Capacity") String engine_Capacity, @RequestParam("gear_Type") String gear_Type,
			@RequestParam("image_URL") String image_URL) {
		String service = carService.updateNew(carId, carName, carCompany, fuel_Type, pow, breakSystem, showroom_Price,
				mileage, seating_Capacity, engine_Capacity, gear_Type, image_URL);
		ModelAndView model = new ModelAndView();
		model.setViewName("Edit.html");
		model.addObject("error", service);
		return model;
	}

	@ResponseBody
	@PostMapping("/delete")
	public ModelAndView deleteControl(@RequestParam("carId") int carId) {
		String service = carService.deleteService(carId);
		ModelAndView model = new ModelAndView();
		model.setViewName("delete.html");
		model.addObject("deleted", service);
		model.addObject("operation", "delete");
		return model;
	}

	@RequestMapping("/")
	public ModelAndView getUi() {
		ModelAndView model = new ModelAndView();
		model.setViewName("front.html");
		return model;
	}

	@RequestMapping("/login")
	public ModelAndView logout() {
		ModelAndView model = new ModelAndView();
		model.setViewName("form.html");
		return model;
	}

	@RequestMapping("/updateTest")
	public ModelAndView updateTest() {
		ModelAndView model = new ModelAndView();
		model.setViewName("Edit.html");
		return model;
	}

	@ResponseBody
	@RequestMapping("/updateLogic")
	public ModelAndView updateLogic(@RequestParam("carId") String id) {
		carId = id;
		Car_Details list = carService.upTestlogic(id);
		ModelAndView model = new ModelAndView();
		model.setViewName("Edit.html");
		if (list == null) {
			model.addObject("error", "NO macthing found");
		} else if (!list.isActive()) {
			model.addObject("error", "NO macthing found");
		} else {
			model.addObject("carId", list.getCarId());
			model.addObject("carName", list.getCarName());
			model.addObject("carCompany", list.getCarCompany());
			model.addObject("fuel_Type", list.getFuel_Type());
			model.addObject("powerSteering", list.isPowerSteering());
			model.addObject("breakSystem", list.getBreakSystem());
			model.addObject("showroom_Price", (int) (list.getShowroom_Price()));
			model.addObject("onroad_Price", list.getOnroad_Price());
			model.addObject("mileage", list.getMileage());
			model.addObject("seating_Capacity", list.getSeating_Capacity());
			model.addObject("engine_Capacity", list.getEngine_Capacity());
			model.addObject("gear_Type", list.getGear_Type());
			model.addObject("active", list.isActive());
			model.addObject("image_URL", list.getImage_URL());
		}
		return model;
	}

	@RequestMapping("/insertPage")
	public String insertPage() {
		return "insert.html";
	}

	@RequestMapping("/deletePage")
	public String deletePage() {
		return "delete.html";
	}

	@RequestMapping("/searchPage")
	public String searchPage() {
		return "search.html";
	}

	@RequestMapping("/searchInPage")
	public String searchInPage() {
		return "searchIn.html";
	}

	@RequestMapping("/prelogout")
	public String prelogout() {
		return "logout.html";
	}
	
	@ResponseBody
	@RequestMapping("/forgot")
	public ModelAndView forgot(@RequestParam("adminUserName") String adminUserName) {
		this.adminUserName=adminUserName;
		ModelAndView model = new ModelAndView();
		String service = carService.forgotPwdService(adminUserName);
		model.setViewName("form.html");
		model.addObject("loginStatus", service);
	return model;
	}
	
	@ResponseBody
	@PostMapping("/toChangepwd")
	public ModelAndView toChangepwd(@RequestParam("adminUserName") String adminUserName, @RequestParam("password") String password,
			@RequestParam("confirm") String confirm) {

		boolean updated = carService.changePwdService(adminUserName, password, confirm);

		ModelAndView model = new ModelAndView();

		if (updated) {
			model.setViewName("pwdchange.html");
			model.addObject("currentUser", adminUserName);
			model.addObject("message", "password has been updated sucessfully click on back to login again");

		} else {
			model.setViewName("pwdchange.html");
			model.addObject("currentUser", adminUserName);
			model.addObject("errmessage", "new and confirm pwd should be same");
		}
		return model;
	}
	
	@RequestMapping("/pwdchange")
	public String getEditProfilePage(Model model) {
		model.addAttribute("currentUser", adminUserName);
		return "pwdchange.html";
	}
}
