package com.smartparking.notification_service.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class KafkaConsumerService {

    private final List<String> notifications = new ArrayList<>();

    // Metodo per ascoltare i messaggi Kafka
    @KafkaListener(topics = "parking-topic", groupId = "notification-group")
    public void consume(String message) {
        // Aggiunge il messaggio ricevuto alla lista delle notifiche
        notifications.add(message);
        System.out.println("Messaggio ricevuto: " + message);
    }

    // Metodo per ottenere tutte le notifiche
    public List<String> getAllNotifications() {
        return notifications;
    }

    // Metodo per aggiungere una notifica manualmente (per l'API POST)
    public void addNotification(String notification) {
        notifications.add(notification);
    }
}