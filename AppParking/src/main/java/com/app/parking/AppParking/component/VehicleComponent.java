package com.app.parking.AppParking.component;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.app.parking.AppParking.dominio.Vehicle;
import com.app.parking.AppParking.repository.VehicleRepository;

@Component
public class VehicleComponent implements CommandLineRunner{

	private VehicleRepository vehicleRepository;

	public VehicleComponent(VehicleRepository vehicleRepository) {
		this.vehicleRepository = vehicleRepository;
	}

	@Override
	public void run(String... args) throws Exception {
	}
	
	
	
}
