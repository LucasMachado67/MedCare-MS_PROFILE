package com.ms.patient.producers;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.ms.patient.events.UserCreationEvent;
import com.ms.patient.models.Assistant;
import com.ms.patient.models.Medic;
import com.ms.patient.models.Patient;

/**
 * Componente responsável por produzir e enviar eventos de criação de usuário
 * para o broker RabbitMQ de forma assíncrona.
 *
 * <p>Estes eventos são consumidos pelo Serviço de Autenticação (Auth Service)
 * para criar as credenciais de login para novos Médicos e Pacientes.</p>
 */
@Component
public class UserCreationProducer {

    final RabbitTemplate rabbitTemplate;

    /**
     * Construtor para injeção de dependência do {@link RabbitTemplate}.
     *
     * @param rabbitTemplate A instância do template do RabbitMQ para envio de mensagens.
     */
    public UserCreationProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    /** Chave de roteamento para a fila de registro de Médicos, injetada via properties. */
    @Value(value = "${medcare.rabbitmq.queue.medic-registered}")
    private String routingKeyMedic;

    /** Chave de roteamento para a fila de registro de Pacientes, injetada via properties. */
    @Value(value = "${medcare.rabbitmq.queue.patient-registered}")
    private String routingKeyPatient;
    /** Chave de roteamento para a fila de registro de Assistentes, injetada via properties. */
    @Value(value = "${medcare.rabbitmq.queue.assistant-registered}")
    private String routingKeyAssistant;

    /**
     * Constrói e publica um evento de criação de usuário para um novo {@link Medic}.
     *
     * <p>O evento é enviado para a fila definida pela {@code routingKeyMedic} com o papel "MEDIC".</p>
     *
     * @param medic A entidade Medic recém-criada, contendo ID e Email necessários para o evento.
     */
    public void publishUserCreationToMedicEvent(Medic medic) {
        
        var medicEventDto = new UserCreationEvent();
        medicEventDto.setPerson_id(medic.getId());
        medicEventDto.setUsername(medic.getEmail());
        medicEventDto.setRole("MEDIC");
        
        rabbitTemplate.convertAndSend("", routingKeyMedic, medicEventDto);
    }

    /**
     * Constrói e publica um evento de criação de usuário para um novo {@link Assistant}.
     *
     * <p>O evento é enviado para a fila definida pela {@code routingKeyAssistant} com o papel "Assistant".</p>
     *
     * @param Assistant A entidade Assistant recém-criada, contendo ID e Email necessários para o evento.
     */
    public void publishUserCreationToAssistantEvent(Assistant assistant) {
        
        var assistantEventDto = new UserCreationEvent();
        assistantEventDto.setPerson_id(assistant.getId());
        assistantEventDto.setUsername(assistant.getEmail());
        assistantEventDto.setRole("ASSISTANT");
        
        rabbitTemplate.convertAndSend("", routingKeyAssistant, assistantEventDto);
    }

    /**
     * Constrói e publica um evento de criação de usuário para um novo {@link Patient}.
     *
     * <p>O evento é enviado para a fila definida pela {@code routingKeyPatient} com o papel "USER".</p>
     *
     * @param patient A entidade Patient recém-criada, contendo ID e Email necessários para o evento.
     */
    public void publishUserCreationToPatientEvent(Patient patient) {
        
        var patientEventDto = new UserCreationEvent();
        patientEventDto.setPerson_id(patient.getId());
        patientEventDto.setUsername(patient.getEmail());
        patientEventDto.setRole("USER");
        
        rabbitTemplate.convertAndSend("", routingKeyPatient, patientEventDto);
    }
}
