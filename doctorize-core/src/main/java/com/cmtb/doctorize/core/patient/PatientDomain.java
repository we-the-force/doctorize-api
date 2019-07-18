/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmtb.doctorize.core.patient;

import com.cmtb.doctorize.domain.patient.Patient;
import com.cmtb.doctorize.domain.patient.PatientContainerDisplayObject;

/**
 *
 * @author pc
 */
public interface PatientDomain {

    public Patient save(Patient patient);
    
    public PatientContainerDisplayObject loadCollectionPatient(Long patientId);
    
}
