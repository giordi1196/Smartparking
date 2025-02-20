package com.smartparking.parking_service.controller;

import org.springframework.web.bind.annotation.*;

import com.smartparking.parking_service.model.Parking;
import com.smartparking.parking_service.service.ParkingService;

import java.util.List;

@RestController
@RequestMapping("/parkings")
public class ParkingController {

    private final ParkingService parkingService;

    public ParkingController(ParkingService parkingService) {
        this.parkingService = parkingService;
    }

    @GetMapping
    public List<Parking> getAllParkings() {
        return parkingService.getAllParkings();
    }
    
    // Endpoint per ottenere un parcheggio specifico tramite ID
    @GetMapping("/{id}")
    public Parking getParkingById(@PathVariable String id) {
        return parkingService.getParkingById(id)
                .orElseThrow(() -> new RuntimeException("Parking not found"));
    }

    @PostMapping
    public Parking addParking(@RequestBody Parking parking) {
        return parkingService.addParking(parking);
    }
}
