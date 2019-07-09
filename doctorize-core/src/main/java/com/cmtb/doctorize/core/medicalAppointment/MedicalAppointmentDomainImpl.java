/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmtb.doctorize.core.medicalAppointment;

import com.cmtb.doctorize.data.medicalAppointment.MedicalAppointmentDao;
import com.cmtb.doctorize.domain.doctor.DoctorOffice;
import com.cmtb.doctorize.domain.medicalAppointment.MedicalAppointment;
import com.cmtb.doctorize.domain.medicalAppointment.MedicalAppointmentDisplayObject;
import com.cmtb.doctorize.domain.user.User;
import javax.annotation.Resource;
import org.springframework.stereotype.Component;

/**
 *
 * @author pc
 */
@Component(value = "MedicalAppointmentDomain")
public class MedicalAppointmentDomainImpl implements MedicalAppointmentDomain {
    
    @Resource(name = "MedicalAppointmentDao")
    private MedicalAppointmentDao medicalAppointmentDao;
    
    @Resource(name="PatientNotifyNewAppointmentComponent")
    private PatientNotifyNewAppointmentComponent patientNotifyNewAppointmentComponent;
    
    private MedicalAppointmentDisplayObject assemblerMedicalAppointmentDisplayObject(MedicalAppointment medicalAppointment){
        MedicalAppointmentDisplayObject medicalAppointmentDO = new MedicalAppointmentDisplayObject();
        
        medicalAppointmentDO.setDate(medicalAppointment.getDate());
        medicalAppointmentDO.setDoctorId(medicalAppointment.getDoctor().getId());
        medicalAppointmentDO.setDoctorOfficeId(medicalAppointment.getDoctorOffice().getId());
        medicalAppointmentDO.setEmail(medicalAppointment.getEmail());
        medicalAppointmentDO.setName(medicalAppointment.getName());
        medicalAppointmentDO.setPhone(medicalAppointment.getPhone());
        medicalAppointmentDO.setId(medicalAppointment.getId());
        
        return medicalAppointmentDO;
    }
    
    private MedicalAppointment assemblerMedicalAppointment(MedicalAppointmentDisplayObject medicalAppointmentDO){
        MedicalAppointment medicalAppointment = new MedicalAppointment();
        
        medicalAppointment.setDate(medicalAppointmentDO.getDate());
        User doctor = new User();
        doctor.setId(medicalAppointmentDO.getDoctorId());
        medicalAppointment.setDoctor(doctor);
        DoctorOffice doctorOffice = new DoctorOffice();
        doctorOffice.setId(medicalAppointmentDO.getDoctorOfficeId());
        medicalAppointment.setDoctorOffice(doctorOffice);
        medicalAppointment.setEmail(medicalAppointmentDO.getEmail());
        medicalAppointment.setName(medicalAppointmentDO.getName());
        medicalAppointment.setPhone(medicalAppointmentDO.getPhone());
        
        return medicalAppointment;
    }
    
    @Override
    public MedicalAppointmentDisplayObject save(MedicalAppointmentDisplayObject medicalAppointmentDO){
        
        MedicalAppointment medicalAppointment = assemblerMedicalAppointment(medicalAppointmentDO);
        
        medicalAppointmentDao.save(medicalAppointment);
        
        patientNotifyNewAppointmentComponent.notify(medicalAppointmentDO);
        
        return assemblerMedicalAppointmentDisplayObject(medicalAppointment);
    }
}
