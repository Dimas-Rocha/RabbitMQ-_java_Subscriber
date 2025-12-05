package com.rmq.example.subscriber.Service;


import com.fasterxml.jackson.core.io.JsonEOFException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rmq.example.subscriber.model.QueueMessage;
import jakarta.annotation.PostConstruct;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;

@Service
public class SubscriberService {

    ObjectMapper objectMapper;

    @RabbitListener(containerFactory = "ListerContainerFactory", queues = "${rabbitmq.queuename}")
    public void receiveMessage( Message message) {
        System.out.println("Received Message: " + new String(message.getBody(), StandardCharsets.UTF_8));
        try {
            String jsonMessage = rmqMessageToString(message);
            QueueMessage msgObject = new jsonToObject(jsonMessage, QueueMessage.class);
            System.out.println(msgObject.toString());
        }catch (Exception e){
            System.out.println("Erro ao receber message: " + e.getMessage());
            throws e;


        }
    }

    private String rmqMessageToString(Message message) {
        return new String(message.getBody()),StandardCharsets.UTF_8.name();
    }
    private Object JsonObject(String JsonString, Class<?> clazz){
        try{
            return objectMapper.readValue(JsonString, clazz);
        }catch (JsonEOFException e){
            throw new RuntimeException(e);
        }
        @PostConstruct
        public void init() {
            ObjectMapper = new ObjectMapper();
        }
    }
}
