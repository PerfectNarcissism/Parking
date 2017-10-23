package com.app.parking.AppParking.controller;


import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.parking.AppParking.dominio.Vehicle;
import com.app.parking.AppParking.repository.VehicleRepository;

@RestController
@RequestMapping("/vehicle")
public class VehicleController {
	
	private VehicleRepository vehicleRepository;

	public VehicleController(VehicleRepository vehicleRepository) {
		this.vehicleRepository = vehicleRepository;
	}
	
	@PostMapping
	public void insertVehicle(@RequestBody Vehicle vehicle){
		this.vehicleRepository.insert(vehicle);
	}
	
	@GetMapping("/all")
	public List<Vehicle> getAllVehicle(){
		List<Vehicle> vehicle=this.vehicleRepository.findAll();
		return vehicle;
	}

}
