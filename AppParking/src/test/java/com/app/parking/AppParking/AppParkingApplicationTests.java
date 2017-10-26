package com.app.parking.AppParking;


import com.app.parking.AppParking.controller.VehicleController;
import com.app.parking.AppParking.dominio.Vehicle;
import static com.app.parking.AppParking.dominio.VehicleBuilder.anVehicle;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
//HOLA
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AppParkingApplicationTests {

	VehicleController vehicleController=new VehicleController();
	Vehicle vehicle=anVehicle().build();

	@Test
	public void getValidVehicleTest(){
		//Arrange
		boolean resp=false;
		//Act
		resp=vehicleController.getValidVehicle(vehicle.getTipo());
		//Assert
		assertEquals(true, resp);
	}
	
	@Test
	public void getValidVehicleTestFailed(){
		//Arrange
		boolean resp=false;
		vehicle=anVehicle().withTipo("Camión").build();
		//Act
		resp=vehicleController.getValidVehicle(vehicle.getTipo());
		//Assert
		assertEquals(false, resp);
	}
	
	@Test
	public void getPlacaTest(){
		//Arrange
		boolean resp=false;
		//Act
		resp=vehicleController.getPlaca(vehicle.getPlaca());
		//Assert
		assertEquals(true, resp);
	}
	
	@Test
	public void getPlacaTestFailed(){
		//Arrange
		boolean resp=false;
		vehicle=anVehicle().withPlaca("AQW-596").build();
		//Act
		resp=vehicleController.getPlaca(vehicle.getPlaca());
		//Assert
		assertEquals(false, resp);
	}

}
