/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmtb.doctorize.core.patient;

import com.cmtb.doctorize.core.medicalAppointment.MedicalAppointmentDomain;
import com.cmtb.doctorize.data.patient.PatientDao;
import com.cmtb.doctorize.domain.patient.Patient;
import com.cmtb.doctorize.domain.patient.PatientContainerDisplayObject;
import com.cmtb.doctorize.domain.shared.BloodTypeEnum;
import com.cmtb.doctorize.domain.shared.GenderEnum;
import com.cmtb.doctorize.domain.shared.MaritalStatusEnum;
import com.cmtb.doctorize.domain.patient.PatientDisplayObject;
import com.cmtb.doctorize.domain.shared.ItemNotFoundException;
import com.cmtb.doctorize.domain.utilities.AttachmentResultDisplayObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Component;

/**
 *
 * @author pc
 */
@Component(value = "PatientDomain")
public class PatientDomainImpl implements PatientDomain {
    
    @Resource(name = "PatientDao")
    private PatientDao patientDao;
    
    @Resource(name = "MedicalAppointmentDomain")
    private MedicalAppointmentDomain medicalAppointmentDomain;
    
    @Resource(name = "PatientAttachmentImagesComponent")
    private PatientAttachmentImagesComponent patientAttachmentImagesComponent;
    
    private PatientDisplayObject assemblerPatientDisplayObject(Patient patient){
        PatientDisplayObject patientDO = new PatientDisplayObject();
        
        if(patient != null){
            patientDO.setId(patient.getId());
            patientDO.setBirthdate(patient.getBirthdate());
            patientDO.setBloodPressure(patient.getBloodPressure());
            patientDO.setBloodType(patient.getBloodType());
            patientDO.setCellphone(patient.getCellphone());
            patientDO.setEmail(patient.getEmail());
            patientDO.setGender(patient.getGender());
            patientDO.setHeight(patient.getHeight());
            patientDO.setMaritalStatus(patient.getMaritalStatus());
            patientDO.setName(patient.getName());
            patientDO.setPhoto(patient.getPhoto());
            patientDO.setWeight(patient.getWeight());
        }
        
        return patientDO;
    }
    
    private Patient assemblerPatientDOtoPatient(PatientDisplayObject patientDO){
        
        Patient patient = new Patient();
        
        patient.setBirthdate(patientDO.getBirthdate());
        patient.setBloodPressure(patientDO.getBloodPressure());
        patient.setBloodType(patientDO.getBloodType());
        patient.setCellphone(patientDO.getCellphone());
        patient.setEmail(patientDO.getEmail());
        patient.setGender(patientDO.getGender());
        patient.setHeight(patientDO.getHeight());
        patient.setId(patientDO.getId());
        patient.setImageData(patientDO.getImageData());
        patient.setMaritalStatus(patientDO.getMaritalStatus());
        patient.setName(patientDO.getName());
        patient.setPhoto(patientDO.getPhoto());
        patient.setWeight(patientDO.getWeight());
        
        return patient;
    }
    
    @Override
    public PatientDisplayObject save(PatientDisplayObject patientDO){
        
        Patient patient = assemblerPatientDOtoPatient(patientDO);
        
        String email = (patient.getEmail() != null ? patient.getEmail().toLowerCase() : "");
        patient.setEmail(email);
        
        if(patient.getId() == null){
            
            patientDao.save(patient);
            
            AttachmentResultDisplayObject results = patientAttachmentImagesComponent.attachementPhoto(patient);
                    
            if (results.getUpdated()) {
                patientDO.setPhoto(results.getPath());
            }
            
        }
        
        patientDO.setImageData("");
        patientDO.setId(patient.getId());
        
        return patientDO;
    }
    
    @Override
    public PatientContainerDisplayObject loadCollectionPatient(Long patientId){
        PatientContainerDisplayObject container = new PatientContainerDisplayObject();
        
        container.setGenders(GenderEnum.getListGenders());
        container.setBloodTypes(BloodTypeEnum.getListBloodType());
        container.setMaritalStatus(MaritalStatusEnum.getListMaritalStatus());
        
        Patient patient = patientDao.getPatientById(patientId);
        container.setPatient(this.assemblerPatientDisplayObject(patient));
        
        return container;
    }
    
    @Override
    public PatientDisplayObject getById(Long patientId){
        Patient patient = patientDao.getPatientById(patientId);
        if(patient == null){
            throw new ItemNotFoundException();
        }
        return this.assemblerPatientDisplayObject(patient);
    }
    
    @Override
    public List<PatientDisplayObject> getListByDoctorId(Long doctorId){
        List<PatientDisplayObject> patientsDO = new ArrayList<>();
        
        List<Patient> patients = patientDao.getListByDoctorId(doctorId);
        
        for(Patient patientItem: patients){
            patientsDO.add(this.assemblerPatientDisplayObject(patientItem));
        }
        
        return patientsDO;
    }
    
    @Override
    public Boolean delete(Long patientId){
        Boolean result = patientDao.delete(patientId);
        if(!result){
            throw new ItemNotFoundException();
        }
        return result; 
    }
    
    @Override
    public Boolean patch(Map<String, Object> patientDOMap){
        
        if(!patientDOMap.containsKey("id")){
            throw new ItemNotFoundException();
        }
        
        if(patientDOMap.size() < 2){
            return false;
        }
        
        if(patientDOMap.containsKey("imageData")){
            Patient patient = new Patient();
            patient.setId((Long)patientDOMap.get("id"));
            patient.setImageData(patientDOMap.get("imageData").toString());
            
            AttachmentResultDisplayObject results = patientAttachmentImagesComponent.attachementPhoto(patient);

            if (results.getUpdated()) {
                patient.setPhoto(results.getPath());
                this.updatePhoto(patient);
            }
        }
        
        if(patientDOMap.size() == 2 && patientDOMap.containsKey("imageData")){
            return true;
        }
        
        return patientDao.patch(patientDOMap);
    }
    
    private boolean updatePhoto(Patient patient){
        return patientDao.updatePhoto(patient);
    }
    
    @Override
    public List<PatientDisplayObject> getByFilter(String filter, String value){
        List<Patient> patients = patientDao.getByFilter(filter, value);
        List<PatientDisplayObject> patientsDO = new ArrayList<>();
        for(Patient patientItem: patients){
            patientsDO.add(this.assemblerPatientDisplayObject(patientItem));
        }
        return patientsDO;
    }
    
    @Override
    public List<PatientDisplayObject> getListByLimit(Integer offset, Integer limit){
        List<Patient> patients = patientDao.getListByLimit(offset, limit);
        List<PatientDisplayObject> patientsDO = new ArrayList<>();
        for(Patient patientItem: patients){
            patientsDO.add(this.assemblerPatientDisplayObject(patientItem));
        }
        return patientsDO;
    }
    
}
