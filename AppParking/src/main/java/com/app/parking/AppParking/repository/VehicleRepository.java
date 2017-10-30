package com.app.parking.AppParking.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.app.parking.AppParking.dominio.Vehicle;

@Repository
public interface VehicleRepository extends MongoRepository<Vehicle, String> {
	List<Vehicle> findByTipo(String tipo);
	List<Vehicle> deleteByPlaca(String placa);
	Vehicle findByPlaca(String placa);
}
