package com.app.parking.AppParking;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.app.parking.AppParking.controller.VehicleController;
import com.app.parking.AppParking.dominio.Vehicle;

import static com.app.parking.AppParking.dominio.VehicleBuilder.anVehicle;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AppParkingIntegrationTests {

	@Autowired
	VehicleController vehicleController;
	
	@Test
	public void insertVehicleTest(){
		Vehicle vehicle= anVehicle().build();
		String message="";
		message=vehicleController.insertVehicle(vehicle);
		assertEquals("Un vehículo ha ingresado", message);
	}
	
	@Test
	public void getAllVehiclesTest(){
		assertNotNull(vehicleController.getAllVehicle());
	}
	
	@Test
	public void deleteVehicleTest(){
		Vehicle vehicle= anVehicle().build();
		assertNotEquals("Ha ocurrido un error.", vehicleController.deleteVehicle(vehicle.getPlaca()));
	}
	
	@Test
	public void getAllVehicleWithFieldsTest(){
		assertNotNull(vehicleController.getAllWithFields());
	}
	
	@Test
	public void priceCarMoreADayTest(){
		assertEquals(vehicleController.priceCar(25), 33000);
	}
	
	@Test
	public void priceCarLessADayTest(){
		assertEquals(vehicleController.priceCar(20), 8000);
	}
	
	@Test
	public void priceBikeHourTest(){
		assertEquals(vehicleController.priceBike(5, 100), 2500);
	}
	
	@Test
	public void priceBikeMoreADayTest(){
		assertEquals(vehicleController.priceBike(10, 550), 2600);
	}

}
