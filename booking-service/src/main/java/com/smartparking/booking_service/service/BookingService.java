package com.smartparking.booking_service.service;

import com.smartparking.booking_service.model.Booking;
import com.smartparking.booking_service.repository.BookingRepository;

import java.util.List;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;
    private final RestTemplate restTemplate;
    private final KafkaTemplate<String, String> kafkaTemplate;

    public BookingService(BookingRepository bookingRepository, RestTemplate restTemplate, KafkaTemplate<String, String> kafkaTemplate) {
        this.bookingRepository = bookingRepository;
        this.restTemplate = restTemplate;
        this.kafkaTemplate = kafkaTemplate;
    }

    public Booking createBooking(Booking booking) {
        // Verifica disponibilità parcheggio tramite una chiamata HTTP al Parking Service
        String parkingServiceUrl = "http://localhost:8082/parkings/" + booking.getParkingId();
        Boolean isAvailable = restTemplate.getForObject(parkingServiceUrl, Boolean.class);

        if (isAvailable == null || !isAvailable) {
            throw new RuntimeException("Parcheggio non disponibile.");
        }

        // Conferma la prenotazione
        booking.setConfirmed(true);
        Booking savedBooking = bookingRepository.save(booking);

        // Invia un messaggio a Kafka per notificare gli altri microservizi (ad esempio Notification Service)
        kafkaTemplate.send("booking-topic", "Prenotazione confermata per l'utente: " + booking.getUserName());

        return savedBooking;
    }

    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }
}
