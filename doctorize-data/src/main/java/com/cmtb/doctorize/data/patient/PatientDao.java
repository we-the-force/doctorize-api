/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmtb.doctorize.data.patient;

import com.cmtb.doctorize.domain.patient.Patient;
import java.util.List;

/**
 *
 * @author pc
 */
public interface PatientDao {

    Patient getPatientById(Long patientId);

    Patient save(Patient patient);
    
    public List<Patient> getListByDoctorId(Long doctorId);
    
    public Boolean delete(Long patientId);
}
