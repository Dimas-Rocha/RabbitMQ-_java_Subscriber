package com.rmq.example.subscriber.config;

import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.beans.CachedIntrospectionResults;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Value("${rabbitmq.host}")
    private String hostName;

    @Value("${rabbitmq.port})
    private int port;

    @Value("${rabbirmq.userName}")
    private String userName;

    @Value("${rabbitmq.password}")
    private String password;

    @Value("${rabbitmq.queuename}")
    private String queueName;

    @Bean
    public CathingConnectionFactory connectionFactory() throws Exception{
        CachingConnectionFactory connectionFactory=new CachingConnectionFactory(hostName);
        connectionFactory.setUsername(userName);
        connectionFactory.setPassword(password);
        connectionFactory.setPort(port);
        return connectionFactory;

    }
    @Bean
    public SimpleRabbitListenerContainerFactory listenerContainerFactory(
            ConnectionFactory connectionFactoryI){
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactoryI);
        return factory;
    }
    )

}
