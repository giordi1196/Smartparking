package com.smartparking.booking_service.repository;

import com.smartparking.booking_service.model.Booking;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BookingRepository extends MongoRepository<Booking, String> {

}