package com.app.parking.AppParking;


import com.app.parking.AppParking.controller.VehicleController;
import com.app.parking.AppParking.dominio.Vehicle;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AppParkingApplicationTests {

	VehicleController vehicleController=new VehicleController();
	
	@MockBean
	Vehicle vehicle;
	
	@Test
	public void getValidVehicleTest(){
		//Arrange
		boolean resp=false;
		when(vehicle.getTipo()).thenReturn("Carro");
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

}
