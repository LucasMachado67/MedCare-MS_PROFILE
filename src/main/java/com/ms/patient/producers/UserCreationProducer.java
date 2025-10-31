package com.ms.patient.producers;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.ms.patient.events.UserCreationEvent;
import com.ms.patient.models.Medic;
import com.ms.patient.models.Patient;

@Component
public class UserCreationProducer {

    final RabbitTemplate rabbitTemplate;

    public UserCreationProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Value(value = "${medcare.rabbitmq.queue.medic-registered}")
    private String routingKeyMedic;

    @Value(value = "${medcare.rabbitmq.queue.patient-registered}")
    private String routingKeyPatient;

    public void publishUserCreationToMedicEvent(Medic medic) {
        
        var medicEventDto = new UserCreationEvent();
        medicEventDto.setPerson_id(medic.getId());
        medicEventDto.setUsername(medic.getEmail());
        medicEventDto.setRole("MEDIC");
        
        rabbitTemplate.convertAndSend("", routingKeyMedic, medicEventDto);
    }

    public void publishUserCreationToPatientEvent(Patient patient) {
        
        var patientEventDto = new UserCreationEvent();
        patientEventDto.setPerson_id(patient.getId());
        patientEventDto.setUsername(patient.getEmail());
        patientEventDto.setRole("USER");
        
        rabbitTemplate.convertAndSend("", routingKeyPatient, patientEventDto);
    }
}
