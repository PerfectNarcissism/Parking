package com.app.parking.controller;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.parking.dominio.Vehicle;
import com.app.parking.repository.VehicleRepository;

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

}
