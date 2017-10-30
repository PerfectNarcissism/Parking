package com.app.parking.AppParking.controller;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import static java.lang.Math.toIntExact;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date today=new Date();
		String message="";
		if(getPlaca(vehicle.getPlaca())){
			vehicle.setFecha(sdf.format(today));
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
	
	@DeleteMapping("/delete/{placa}")
	public String deleteVehicle(@PathVariable String placa){
		String message="";
		int total=0;
		try {
			Vehicle vehicle=this.vehicleService.serviceFindByPlaca(placa);
			Long totalHours=calculateTime(vehicle);
			if(totalHours>=0){
				total=calculatePrice(totalHours, vehicle);
				this.vehicleService.serviceDeleteVehicle(placa);
				message= "Se ha retirado un vehículo. Debe pagar: "+total;
			}else{
				message="No se pudo calcular la hora.";
			}
			
		} catch (Exception e) {
			message= "Ha ocurrido un error.";
		}
		return message;
	}
	
	public Long calculateTime(Vehicle vehicle) throws ParseException{ 
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date carDate=sdf.parse(vehicle.getFecha());
		Date today = new Date();
		return TimeUnit.MILLISECONDS.toHours(today.getTime()-carDate.getTime());
	}
	
	public int calculatePrice(Long totalHours, Vehicle vehicle){
		int hours = toIntExact(totalHours);
		hours=(hours==0)?1:hours;
		int total=0;
		if(vehicle.getTipo().equals("Carro")){
			total=priceCar(hours);
		}else if(vehicle.getTipo().equals("Moto")){			
			total=priceBike(hours, vehicle.getCilindraje());
		}
		return total;
	}
	
	public int priceCar(int hours){
		int total=0;
		if(hours<9){
			total=hours*1000;
		}else{
			int dayValue=8000;
			if(hours>24){
				int totalDays=Math.abs(hours/12);
				total=(dayValue+(hours*1000))*totalDays;
			}else{
				total=dayValue;
			}
		}
		return total;
	}
	
	public int priceBike(int hours, int cilindraje){
		int total=0;
		if(hours<9){
			total=hours*500;
		}else{
			int dayValue=600;
			int totalDays=Math.abs(hours/12);
			total=totalDays*dayValue;
		}
		
		if(cilindraje>500){
			total=total+2000;
		}
		
		return total;
	}

}
