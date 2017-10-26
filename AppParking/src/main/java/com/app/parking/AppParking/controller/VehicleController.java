package com.app.parking.AppParking.controller;


import java.util.Calendar;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.parking.AppParking.dominio.Vehicle;
import com.app.parking.AppParking.service.VehicleService;

@RestController
@RequestMapping("/vehicle")
public class VehicleController {
	
	private VehicleService vehicleService;
	

	public VehicleController(VehicleService vehicleService) {
		this.vehicleService = vehicleService;
	}
	
	@PostMapping
	public String insertVehicle(@RequestBody Vehicle vehicle){
		String message="";
		try {
			if(getValidVehicle(vehicle.getTipo())){
				message=validateSpace(vehicle);
			}else{
				message="Tipo de vehículo no válido.";
			}
		} catch (Exception e) {
			message="Ha ocurrido un error: "+ e.toString();
		}
		return message;
	}
	
	@GetMapping("/all")
	public List<Vehicle> getAllVehicle(){
		return this.vehicleService.serviceGetAllVehicle();
	}
	
	public String setMessage(Vehicle vehicle){
		String message="";
		if(getPlaca(vehicle.getPlaca())){
			Vehicle vehicleResult=this.vehicleService.serviceInsertVehicle(vehicle);
			if(vehicleResult==vehicle){
				message="Un vehículo ha ingresado";
			}else{
				message="El vehículo no pudo ser ingresado";
			}
		}else{
			message="El vehiculo no puede ingresar hoy. El vehiculo no se encuentra en un día hábil.";
		}
		return message;
	}
	
	public Boolean getPlaca(String placa){
		Boolean result=false;
		if(placa.startsWith("A")){
			Calendar calendar = Calendar.getInstance();
			int day = calendar.get(Calendar.DAY_OF_WEEK);
			if(day==Calendar.MONDAY || day==Calendar.SUNDAY){
				result=true;
			}
		}else{
			return true;
		}
		return result;
	}
	
	public boolean getValidVehicle(String tipo){
		boolean result=false;
		if(tipo.equals("Carro") || tipo.equals("Moto")){
			result= true;
		}
		return result;
	}
	
	public String validateSpace(Vehicle vehicle){
		int countVehicles=this.vehicleService.serviceGetCountVehicles(vehicle.getTipo());
		String message="";
		if((vehicle.getTipo().equals("Carro")) && (countVehicles>=20)){
			message="El cupo para carros está lleno.";
		}else if((vehicle.getTipo().equals("Moto")) && (countVehicles>=10)){
			message="El cupo para motos está lleno.";
		}else{
			message=setMessage(vehicle);
		}
		return message;
	}

}
