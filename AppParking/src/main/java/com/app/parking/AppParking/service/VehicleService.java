package com.app.parking.AppParking.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.app.parking.AppParking.dominio.Vehicle;
import com.app.parking.AppParking.repository.VehicleRepository;

@Service
public class VehicleService {
	
	private VehicleRepository vehicleRepository;
	
	public VehicleService(VehicleRepository vehicleRepository) throws Exception {
		this.vehicleRepository = vehicleRepository;
	}

	public List<Vehicle> serviceGetAllVehicle(){
		return this.vehicleRepository.findAll();
	}
	
	public Vehicle serviceInsertVehicle(Vehicle vehicle){
		return this.vehicleRepository.insert(vehicle);
	}
	
	public int serviceGetCountVehicles(String tipo){
		return this.vehicleRepository.findByTipo(tipo).size();
	}
	
}
