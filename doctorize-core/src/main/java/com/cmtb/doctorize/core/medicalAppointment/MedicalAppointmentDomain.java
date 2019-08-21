/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmtb.doctorize.core.medicalAppointment;

import com.cmtb.doctorize.domain.medicalAppointment.MedicalAppointmentDisplayObject;
import java.util.List;

/**
 *
 * @author pc
 */
public interface MedicalAppointmentDomain {

    public MedicalAppointmentDisplayObject save(MedicalAppointmentDisplayObject medicalAppointment);
    
    public List<MedicalAppointmentDisplayObject> getListByDoctorId(Long doctorId);
    
    public MedicalAppointmentDisplayObject getById(Long appointmentId);
    
    public Boolean delete(Long appointmentId);
    
    public Boolean deleteByPatientId(Long patientId);
    
    public Boolean update(MedicalAppointmentDisplayObject medicalAppointmentDO);
    
    public Boolean setPatient(Long patientId, Long appoinmentId);
    
    public List<MedicalAppointmentDisplayObject> getListByFilters(Long doctorId, List<String> filter, List<String> search);
    
}
