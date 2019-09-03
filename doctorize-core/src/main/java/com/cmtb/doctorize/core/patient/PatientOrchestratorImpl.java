/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmtb.doctorize.core.patient;

import com.cmtb.doctorize.core.medicalAppointment.MedicalAppointmentDomain;
import com.cmtb.doctorize.domain.patient.Patient;
import com.cmtb.doctorize.domain.patient.PatientDisplayObject;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author gealtec
 */
@Component(value = "PatientOrchestrator")
public class PatientOrchestratorImpl implements PatientOrchestrator {
    
    @Resource(name = "PatientDomain")
    private PatientDomain patientDomain;
    
    @Resource(name = "MedicalAppointmentDomain")
    private MedicalAppointmentDomain medicalAppointmentDomain;
    
    @Transactional
    @Override
    public PatientDisplayObject save(PatientDisplayObject patientDO){
        return patientDomain.save(patientDO);
    }
    
    @Transactional
    @Override
    public Boolean delete(Long patientId){
        medicalAppointmentDomain.deleteByPatientId(patientId);
        return patientDomain.delete(patientId);
    }
    
    @Transactional
    @Override
    public Boolean patch(Map<String,Object> patientDOMap){
        return patientDomain.patch(patientDOMap);
    }
}
