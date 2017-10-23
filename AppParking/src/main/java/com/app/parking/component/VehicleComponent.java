package com.app.parking.component;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.app.parking.dominio.Vehicle;
import com.app.parking.repository.VehicleRepository;

@Component
public class VehicleComponent implements CommandLineRunner{

	private VehicleRepository vehicleRepository;

	public VehicleComponent(VehicleRepository vehicleRepository) {
		this.vehicleRepository = vehicleRepository;
	}

	@Override
	public void run(String... args) throws Exception {
		Vehicle vehicle=new Vehicle("Renault", "2014", "Rojo", "FKD-837");
		this.vehicleRepository.deleteAll();
		this.vehicleRepository.save(vehicle);
	}
	
	
	
}
