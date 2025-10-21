package com.ms.patient.producers;

import com.ms.patient.events.MedicRegisteredEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;

import java.util.function.Supplier;

@Configuration
public class MedicProducerConfig {

    // O canal será acessado através do nome "medicRegistrationSupplier"
    // O nome do método é [nome do binding] + Supplier/Consumer/Function
    //@Bean
    public Supplier<Message<MedicRegisteredEvent>> medicRegistrationSupplier() {
        // Inicialmente, retorna um Supplier que não envia nada (é um "dummy").
        // O Service real enviará as mensagens.
        return () -> null;
    }
}
