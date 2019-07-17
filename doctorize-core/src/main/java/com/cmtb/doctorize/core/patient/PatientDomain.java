/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmtb.doctorize.core.patient;

import com.cmtb.doctorize.domain.patient.Patient;
import com.cmtb.doctorize.domain.patient.PatientDisplayObject;

/**
 *
 * @author pc
 */
public interface PatientDomain {

    public Patient save(Patient patient);
    
    public PatientDisplayObject getById(Long patientId);
    
}
