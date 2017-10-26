package com.app.parking.AppParking;


import com.app.parking.AppParking.controller.VehicleController;
import com.app.parking.AppParking.dominio.Vehicle;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AppParkingApplicationTests {
		
	@MockBean
	Vehicle vehicle;
	@MockBean
	VehicleController vehicleController;
	
	@Test
	public void getValidVehicleTest(){
		//Arrange
		boolean resp=false;
		when(vehicle.getTipo()).thenReturn("Carro");
		when(vehicleController.getValidVehicle(vehicle.getTipo())).thenCallRealMethod();
		//Act
		resp=vehicleController.getValidVehicle(vehicle.getTipo());
		//Assert
		assertEquals(true, resp);
	}
	
	@Test
	public void getValidVehicleTestFailed(){
		//Arrange
		boolean resp=false;
		when(vehicle.getTipo()).thenReturn("Camión");
		//Act
		resp=vehicleController.getValidVehicle(vehicle.getTipo());
		//Assert
		assertEquals(false, resp);
	}
	
	@Test
	public void getPlacaTest(){
		//Arrange
		boolean resp=false;
		when(vehicle.getPlaca()).thenReturn("NAG-547");
		when(vehicleController.getPlaca(vehicle.getPlaca())).thenCallRealMethod();
		//Act
		resp=vehicleController.getPlaca(vehicle.getPlaca());
		//Assert
		assertEquals(true, resp);
	}
	
	@Test
	public void getPlacaTestFailed(){
		//Arrange
		boolean resp=false;
		when(vehicle.getPlaca()).thenReturn("AAG-547");
		//Act
		resp=vehicleController.getPlaca(vehicle.getPlaca());
		//Assert
		assertEquals(false, resp);
	}
	
	@Test
	public void setMessageTest(){
		//Arrange
		String message="";
		when(vehicleController.setMessage(vehicle)).thenReturn("Un vehículo ha ingresado");
		//Act
		message=vehicleController.setMessage(vehicle);
		//Assert
		assertEquals("Un vehículo ha ingresado", message);
	}
	
	@Test
	public void validateSpaceTestCar(){
		//Arrange
		String message="";
		when(vehicleController.validateSpace(vehicle)).thenReturn("El cupo para carros está lleno.");
		//Act
		message=vehicleController.validateSpace(vehicle);
		//Assert
		assertEquals("El cupo para carros está lleno.", message);
	}
	
	public void validateSpaceTestBike(){
		//Arrange
		String message="";
		when(vehicleController.validateSpace(vehicle)).thenReturn("El cupo para motos está lleno.");
		//Act
		message=vehicleController.validateSpace(vehicle);
		//Assert
		assertEquals("El cupo para motos está lleno.", message);
	}
	
}
