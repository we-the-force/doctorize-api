/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmtb.doctorize.core.patient;

import com.cmtb.doctorize.domain.patient.PatientDisplayObject;
import java.util.Map;

/**
 *
 * @author gealtec
 */
public interface PatientOrchestrator {

    public PatientDisplayObject save(PatientDisplayObject patientDO);
    
    public Boolean delete(Long patientId);
    
    public Boolean patch(Map<String,Object> patientDOMap);
    
}
