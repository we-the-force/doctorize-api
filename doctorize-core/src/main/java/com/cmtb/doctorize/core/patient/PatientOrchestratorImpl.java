/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmtb.doctorize.core.patient;

import com.cmtb.doctorize.domain.patient.Patient;
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
    
    @Transactional
    @Override
    public Patient save(Patient patient){
        return patientDomain.save(patient);
    }
}
