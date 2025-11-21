package com.ms.patient.config;

import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Classe de configuração do RabbitMQ para a aplicação de Pacientes.
 *
 * <p>Responsável por definir beans necessários para a serialização e desserialização
 * de mensagens (payloads) que transitam entre a aplicação e o broker RabbitMQ,
 * garantindo a comunicação baseada em JSON.</p>
 *
 * @author Lucas Edson Machado
 * @since 2025-11-17
 */
@Configuration
public class RabbitMQConfig {

    /**
     * Define o bean {@link Jackson2JsonMessageConverter} para ser usado
     * na serialização e desserialização de mensagens.
     *
     * <p>Isso garante que os objetos Java sejam convertidos em JSON antes de serem
     * enviados para as filas do RabbitMQ e vice-versa, utilizando o
     * {@link ObjectMapper} padrão do Jackson.</p>
     *
     * @return Uma instância configurada do conversor de mensagens JSON.
     */
    @Bean
    public Jackson2JsonMessageConverter messageConverter(){
        ObjectMapper objectMapper = new ObjectMapper();
        return new Jackson2JsonMessageConverter(objectMapper);
    }

}
