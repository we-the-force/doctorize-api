/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmtb.doctorize.core.patient;

import com.cmtb.doctorize.domain.patient.Patient;
import com.cmtb.doctorize.domain.patient.PatientDisplayObject;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author gealtec
 */
public interface PatientOrchestrator {

    public PatientDisplayObject save(PatientDisplayObject patientDO);
    
    public Boolean delete(Long patientId);
    
    public PatientDisplayObject update(PatientDisplayObject patientDO);
    
}
