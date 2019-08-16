/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmtb.doctorize.data.medicalAppointment;

import com.cmtb.doctorize.domain.medicalAppointment.MedicalAppointment;
import java.util.List;

/**
 *
 * @author pc
 */
public interface MedicalAppointmentDao {

    public MedicalAppointment save(MedicalAppointment medicalAppointment);
    
    public List<MedicalAppointment> getListByDoctorId(Long doctorId);
    
    public List<MedicalAppointment> getListByFilter(Long doctorId, List<String> filter, List<String> search);
    
    public MedicalAppointment getById(Long appointmentId);
    
    public Boolean delete(Long appointmentId);
    
    public Boolean deleteByPatientId(Long patientId);    
    
    public Boolean update(MedicalAppointment medicalAppointment);    
    
    public Boolean setPatient(Long patientId, Long appoinmentId);
}
