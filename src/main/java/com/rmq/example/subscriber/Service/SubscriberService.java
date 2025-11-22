package com.rmq.example.subscriber.Service;


import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class SubscriberService {
    @RabbitListener(queues = "${rabbitmq.queuename}")
    public void receiveMessage( Messagemessage) {
        System.out.println(message);
    }

}
