/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmtb.doctorize.core.medicalAppointment;

import com.cmtb.doctorize.data.medicalAppointment.MedicalAppointmentDao;
import com.cmtb.doctorize.domain.doctor.DoctorOffice;
import com.cmtb.doctorize.domain.medicalAppointment.MedicalAppoinmentFilterDisplayObject;
import com.cmtb.doctorize.domain.medicalAppointment.MedicalAppointment;
import com.cmtb.doctorize.domain.medicalAppointment.MedicalAppointmentDisplayObject;
import com.cmtb.doctorize.domain.patient.Patient;
import com.cmtb.doctorize.domain.shared.ItemNotFoundException;
import com.cmtb.doctorize.domain.user.User;
import java.util.ArrayList;
import java.util.List;
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
    
    @Resource(name = "MedicalAppoinmentFilterClusterValidator")
    private MedicalAppoinmentFilterClusterValidator medicalAppoinmentFilterClusterValidator;
    
    private MedicalAppointmentDisplayObject assemblerMedicalAppointmentDisplayObject(MedicalAppointment medicalAppointment){
        MedicalAppointmentDisplayObject medicalAppointmentDO = new MedicalAppointmentDisplayObject();
        
        medicalAppointmentDO.setDate(medicalAppointment.getDate());
        medicalAppointmentDO.setDoctorId(medicalAppointment.getDoctor().getId());
        medicalAppointmentDO.setOfficeId(medicalAppointment.getDoctorOffice().getId());
        medicalAppointmentDO.setEmail(medicalAppointment.getEmail());
        medicalAppointmentDO.setName(medicalAppointment.getName());
        medicalAppointmentDO.setPhone(medicalAppointment.getPhone());
        medicalAppointmentDO.setId(medicalAppointment.getId());
        
        return medicalAppointmentDO;
    }
    
    private MedicalAppointment assemblerMedicalAppointment(MedicalAppointmentDisplayObject medicalAppointmentDO){
        MedicalAppointment medicalAppointment = new MedicalAppointment();
        
        medicalAppointment.setId(medicalAppointmentDO.getId());
        medicalAppointment.setDate(medicalAppointmentDO.getDate());
        User doctor = new User();
        doctor.setId(medicalAppointmentDO.getDoctorId());
        medicalAppointment.setDoctor(doctor);
        DoctorOffice doctorOffice = new DoctorOffice();
        doctorOffice.setId(medicalAppointmentDO.getOfficeId());
        medicalAppointment.setDoctorOffice(doctorOffice);
        medicalAppointment.setEmail(medicalAppointmentDO.getEmail());
        medicalAppointment.setName(medicalAppointmentDO.getName());
        medicalAppointment.setPhone(medicalAppointmentDO.getPhone());
        
        if(medicalAppointmentDO.getPatientId() != null){
            Patient patient = new Patient();
            patient.setId(medicalAppointmentDO.getPatientId());
            medicalAppointment.setPatient(patient);
        }
        
        
        return medicalAppointment;
    }
    
    @Override
    public MedicalAppointmentDisplayObject save(MedicalAppointmentDisplayObject medicalAppointmentDO){
        
        MedicalAppointment medicalAppointment = assemblerMedicalAppointment(medicalAppointmentDO);
        
        medicalAppointmentDao.save(medicalAppointment);
        
        patientNotifyNewAppointmentComponent.notify(medicalAppointmentDO);
        
        return assemblerMedicalAppointmentDisplayObject(medicalAppointment);
    }
    
    @Override
    public List<MedicalAppointmentDisplayObject> getListByDoctorId(Long doctorId){
        List<MedicalAppointment> medicalAppointments = medicalAppointmentDao.getListByDoctorId(doctorId);
        
        List<MedicalAppointmentDisplayObject> medicalAppointmentsDO = new ArrayList<>();
        
        for(MedicalAppointment item: medicalAppointments){
            medicalAppointmentsDO.add(this.assemblerMedicalAppointmentDisplayObject(item));
        }
        
        return medicalAppointmentsDO;
    }
    
    @Override
    public MedicalAppointmentDisplayObject getById(Long appointmentId){
        MedicalAppointment medicalAppointment = medicalAppointmentDao.getById(appointmentId);
        if(medicalAppointment == null){
            throw new ItemNotFoundException();
        }
        return assemblerMedicalAppointmentDisplayObject(medicalAppointment);
    }
    
    @Override
    public Boolean delete(Long appointmentId){
        Boolean result = medicalAppointmentDao.delete(appointmentId);
        if(!result){
            throw new ItemNotFoundException();
        }
        return result;
    }
    
    @Override
    public Boolean deleteByPatientId(Long patientId){
        return medicalAppointmentDao.deleteByPatientId(patientId);
    }
    
    @Override
    public Boolean update(MedicalAppointmentDisplayObject medicalAppointmentDO){
        MedicalAppointment medicalAppointment = assemblerMedicalAppointment(medicalAppointmentDO);
        return medicalAppointmentDao.update(medicalAppointment);
    }
    
    @Override
    public Boolean setPatient(Long patientId, Long appoinmentId){
        return medicalAppointmentDao.setPatient(patientId, appoinmentId);
    }
    
    @Override
    public List<MedicalAppointmentDisplayObject> getListByFilters(Long doctorId, List<String> filter, List<String> search){
        
        MedicalAppoinmentFilterDisplayObject model = new MedicalAppoinmentFilterDisplayObject();
        model.setDoctorId(doctorId);
        model.setFilter(filter);
        model.setSearch(search);
        
        medicalAppoinmentFilterClusterValidator.run(model);
        
        List<MedicalAppointment> medicalAppointments = medicalAppointmentDao.getListByFilter(model);
        List<MedicalAppointmentDisplayObject> medicalAppointmentsDO = new ArrayList<>();
        
        for(MedicalAppointment medicalAppointmentItem: medicalAppointments){
            medicalAppointmentsDO.add(assemblerMedicalAppointmentDisplayObject(medicalAppointmentItem));
        }
                
        return medicalAppointmentsDO;
    }
}
