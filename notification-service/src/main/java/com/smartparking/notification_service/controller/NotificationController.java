package com.smartparking.notification_service.controller;

import com.smartparking.notification_service.service.KafkaConsumerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notifications")
public class NotificationController {

    private final KafkaConsumerService kafkaConsumerService;

    // Iniettiamo KafkaConsumerService per utilizzarlo
    public NotificationController(KafkaConsumerService kafkaConsumerService) {
        this.kafkaConsumerService = kafkaConsumerService;
    }

    // Endpoint per ottenere tutte le notifiche (simuliamo una lista di notifiche)
    @GetMapping
    public List<String> getNotifications() {
        // Restituiamo le notifiche in formato lista
        return kafkaConsumerService.getAllNotifications();  // Supponiamo che KafkaConsumerService tenga traccia delle notifiche.
    }

    // Endpoint per aggiungere una notifica (per esempio per simulare un'inserimento di notifica)
    @PostMapping
    public String addNotification(@RequestBody String notification) {
        kafkaConsumerService.addNotification(notification);
        return "Notifica aggiunta: " + notification;
    }
}