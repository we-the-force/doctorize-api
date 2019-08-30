/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmtb.doctorize.core.medicalAppointment;

import com.cmtb.doctorize.domain.medicalAppointment.MedicalAppointmentDisplayObject;
import com.cmtb.doctorize.domain.shared.ItemNotFoundException;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author pc
 */
@Component(value = "MedicalAppointmentOrchestrator")
public class MedicalAppointmentOrchestratorImpl implements MedicalAppointmentOrchestrator {
    
    @Resource(name = "MedicalAppointmentDomain")
    private MedicalAppointmentDomain medicalAppointmentDomain;
    
    @Transactional
    @Override
    public MedicalAppointmentDisplayObject save(MedicalAppointmentDisplayObject medicalAppointment){
        
        return medicalAppointmentDomain.save(medicalAppointment);
    }
    
    @Transactional
    @Override
    public Boolean delete(Long appointmentId){
        return medicalAppointmentDomain.delete(appointmentId);
    }
    
    @Transactional
    @Override
    public Boolean patch(Map<String, Object> medicalAppointmentDOMap){
        return medicalAppointmentDomain.patch(medicalAppointmentDOMap);
    }
}
