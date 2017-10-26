package com.app.parking.AppParking.component;

import java.util.logging.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;



@Component
public class VehicleComponent implements CommandLineRunner{

	private static Logger logger = Logger.getLogger("InfoLogging");
	
	@Override
	public void run(String... args) throws Exception {
		logger.info("Logging an INFO-level message");
	}
	
	
	
}
