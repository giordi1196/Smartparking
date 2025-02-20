package com.smartparking.parking_service.service;

import com.smartparking.parking_service.model.Parking;
import com.smartparking.parking_service.repository.ParkingRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ParkingService {
    private final ParkingRepository parkingRepository;
    private final KafkaProducerService kafkaProducerService;

    public ParkingService(ParkingRepository parkingRepository, KafkaProducerService kafkaProducerService) {
        this.parkingRepository = parkingRepository;
        this.kafkaProducerService = kafkaProducerService;
    }

    public List<Parking> getAllParkings() {
        return parkingRepository.findAll();
    }

    public Parking addParking(Parking parking) {
        Parking savedParking = parkingRepository.save(parking);
        
        // Invia un evento a Kafka
        kafkaProducerService.sendMessage("parking-topic", "Nuovo parcheggio aggiunto: " + savedParking.getName());
        
        return savedParking;
    }
    
    // Ottieni un parcheggio specifico per ID
    public Optional<Parking> getParkingById(String id) {
        return parkingRepository.findById(id);
    }
}
