package com.smartparking.parking_service.service;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService {

    private final KafkaTemplate<String, String> kafkaTemplate;

    // Costruttore per iniettare KafkaTemplate
    public KafkaProducerService(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    // Metodo per inviare un messaggio al topic Kafka
    public void sendMessage(String topic, String message) {
        kafkaTemplate.send(topic, message);
    }
}
