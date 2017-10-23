package com.app.parking.AppParking.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.app.parking.AppParking.dominio.Vehicle;

@Repository
public interface VehicleRepository extends MongoRepository<Vehicle, String> {

}
