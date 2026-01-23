package com.ms.patient.enums;

/**
 * Representa a situação clínica ou o estado atual de acompanhamento de um Paciente.
 *
 * <p>Usado para rastrear e classificar o estado do paciente dentro do sistema de saúde.</p>
 * 
 * Apenas o Médico deve conseguir alterar o estado
 */
public enum PatientSituation {

    /** O paciente está clinicamente estável, sem complicações imediatas. */
    STABLE,
    
    /** O paciente está em observação clínica para monitoramento contínuo. */
    IN_OBS,
    
    /** O paciente está internado em uma unidade hospitalar. */
    HOSPITALIZED,
    
    /** O paciente está atualmente em procedimento cirúrgico ou aguardando. */
    SURGERY,
    
    /** O estado de saúde do paciente requer atenção imediata e intensiva. */
    CRITICAL,
    
    /** O paciente está em fase de recuperação após um procedimento ou evento. */
    RECOVERING,
    
    /** O paciente recebeu alta médica e está sendo liberado. */
    DISCHARGE,
    
    /** O estado de saúde ou a situação do paciente ainda não foi classificado ou registrado. 
     * Esse é o dado padrão que é criado antes do paciente ser consultado
    */
    NOT_DEFINED
}