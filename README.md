RabbitMQ Java Subscriber
Um consumidor RabbitMQ robusto em Java com suporte a Docker, projetado para processamento de mensagens em diferentes padrões de mensageria.
------------------------------------------------------------------------------


🚀 Tecnologias
Java 17+ - Linguagem principal

RabbitMQ 3.8+ - Broker de mensagens

Docker - Containerização

Maven/Gradle - Gerenciamento de dependências

JUnit 5 - Testes unitários


RabbitMQ Java Subscriber
Un consumidor RabbitMQ robusto en Java con soporte para Docker, diseñado para el procesamiento de mensajes en diferentes patrones de mensajería.
-----------------------------------------------------------------------------------------------------------
🚀 Tecnologías

Java 17+ - Lenguaje principal

RabbitMQ 3.8+ - Broker de mensajería

Docker - Containerización

Maven/Gradle - Gestión de dependencias

JUnit 5 - Pruebas unitarias

📋 Índice

Visión General

Funcionalidades

Inicio Rápido

Configuración con Docker

Arquitectura

Patrones de Consumo

API del Subscriber

Recursos Avanzados

Monitorización

Despliegue

Integración con Publisher

Contribuciones

Licencia

🎯 Visión General
Este proyecto implementa un consumidor RabbitMQ en Java con:

Containerización completa mediante Docker

Soporte para todos los tipos de Exchange (Direct, Fanout, Topic, Headers)

Múltiples estrategias de consumo (push/pull)

Reconexión automática

Gestión de errores y dead letter queues

Procesamiento en paralelo y balanceo de carga

Listo para producción con registro y métricas

✨ Funcionalidades
✅ Núcleo

Consumo push y pull de mensajes

Ack manual y automático

QOS (calidad de servicio) configurable

Múltiples workers concurrentes

Tolerancia a fallos y reintentos automáticos

Dead Letter Exchange (DLX) integrado

Health checks y métricas de consumo

