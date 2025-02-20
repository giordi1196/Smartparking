package com.smartparking.parking_service.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.smartparking.parking_service.model.Parking;

public interface ParkingRepository extends MongoRepository<Parking, String> {
}
