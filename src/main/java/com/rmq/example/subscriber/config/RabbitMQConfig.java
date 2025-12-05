package com.rmq.example.subscriber.config;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.rabbit.config.RetryInterceptorBuilder;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.retry.RepublishMessageRecoverer;
import org.springframework.beans.CachedIntrospectionResults;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.interceptor.RetryOperationsInterceptor;

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
            ConnectionFactory connectionFactory, RetryOperationsInterceptor retryOperationsInterceptor) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setConnectionFactory(retryOperationsInterceptor);
        return factory;
    }
    @Bean
    public AmqpAdmin amqpAdmin()throws Exception{
        return new RabbitAdmin(connectionFactory());
    }
    @Bean
    public Queue createQueue() throws Exception{
        Queue q = QueueBuilder.durable().build(queueName)
                .build();
        amqpAdmin().declareQueue(q);

        return q;
    }


    @Bean
    Queue boqQueue() throws Exception{
        Queue q = QueueBuilder.durable(name:"boq."+ queueName)
          .build();
        amqpAdmin().declareQueue(q);
        return q;
    }

    @Bean
    public RepublishMessageRecoverer messageRecoverer(RabbitTemplate rabbitTemplate){
        RepublishMessageRecoverer republishMessageRecoverer = new RepublishMessageRecoverer(rabbitTemplate);
        recoverer.setErrorRoutingKeyPrefix("boq.");
        return recoverer;
    }

    @Bean
    public RetryOperationsInterceptor retryOperationsInterceptor(
        RepublishMessageRecoverer recoverer){
        RetryOperationsInterceptor interceptor = RetryInterceptorBuilder
                .stateless()
                .maxAttempts(2)
                .backOffOptions(initialInterval: 2000, multipiler: 1, maxinterval: 100000)
                .recoverer(recoverer)
                .build();
        return interceptor;

    }




}
