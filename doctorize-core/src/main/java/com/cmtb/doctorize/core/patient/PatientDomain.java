/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmtb.doctorize.core.patient;

import com.cmtb.doctorize.domain.patient.Patient;
import com.cmtb.doctorize.domain.patient.PatientDisplayObject;
import com.cmtb.doctorize.domain.patient.PatientContainerDisplayObject;
import java.util.List;

/**
 *
 * @author pc
 */
public interface PatientDomain {

    public PatientDisplayObject save(PatientDisplayObject patientDO);
    
    public PatientDisplayObject getById(Long patientId);
    
    public PatientContainerDisplayObject loadCollectionPatient(Long patientId);
    
    public List<PatientDisplayObject> getListByDoctorId(Long doctorId);
    
    public Boolean delete(Long patientId);
    
    public PatientDisplayObject update(PatientDisplayObject patientDO);
    
    public List<PatientDisplayObject> getByFilter(String filter, String value);
    
    public List<PatientDisplayObject> getListByLimit(Integer offset, Integer limit);
    
}
