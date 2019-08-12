/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmtb.doctorize.core.assistent;

import com.cmtb.doctorize.data.assistant.AssistantDoctorOfficeDao;
import com.cmtb.doctorize.domain.assistant.AssistantDoctorOffice;
import com.cmtb.doctorize.domain.assistant.AssistantDoctorOfficeDisplayObject;
import com.cmtb.doctorize.domain.doctor.DoctorOffice;
import com.cmtb.doctorize.domain.user.User;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Component;

/**
 *
 * @author gealtec
 */
@Component(value = "AssistantDoctorOfficeDomain")
public class AssistantDoctorOfficeDomainImpl implements AssistantDoctorOfficeDomain {
    
    @Resource(name = "AssistantDoctorOfficeDao")
    private AssistantDoctorOfficeDao assistantDoctorOfficeDao;
    
    private AssistantDoctorOffice assemblerAssistantDOtoAssistantDoctorOffice(AssistantDoctorOfficeDisplayObject assistantDoctorOffcieDO, Long assistantId){
        AssistantDoctorOffice assistantDoctorOffice = new AssistantDoctorOffice();
        
        User assistant = new User();
        assistant.setId(assistantId);
        assistantDoctorOffice.setAssistant(assistant);
        
        User doctor = new User();
        doctor.setId(assistantDoctorOffcieDO.getDoctorId());
        assistantDoctorOffice.setDoctor(doctor);
        
        DoctorOffice doctorOffice = new DoctorOffice();
        doctorOffice.setId(assistantDoctorOffcieDO.getOfficeId());
        assistantDoctorOffice.setDoctorOffice(doctorOffice);
        
        assistantDoctorOffice.setPermissions(assistantDoctorOffcieDO.getPermissions());
        
        return assistantDoctorOffice;
    }
    
    @Override
    public AssistantDoctorOffice save(AssistantDoctorOffice assistantDoctorOffice){
        
        return assistantDoctorOfficeDao.save(assistantDoctorOffice);
    }
    
    @Override
    public List<AssistantDoctorOffice> getListAssistantsByDoctorId(Long doctorId){
        return assistantDoctorOfficeDao.getListAssistantsByDoctorId(doctorId);
    }
    
    @Override
    public Boolean deleteAssistantByIdAndDoctor(Long assistantId, Long doctorId){
        return assistantDoctorOfficeDao.deleteAssistantByIdAndDoctor(assistantId, doctorId);
    }
    
    @Override
    public Boolean addUpdateAssistantOffice(AssistantDoctorOfficeDisplayObject assistantDoctorOfficeDO, Long assistantId){
        
        AssistantDoctorOffice assistantDoctorOffice = this.assemblerAssistantDOtoAssistantDoctorOffice(assistantDoctorOfficeDO, assistantId);
        
        if(assistantDoctorOfficeDao.exists(assistantDoctorOffice)){
            assistantDoctorOfficeDao.delete(assistantDoctorOffice);
        }
        assistantDoctorOfficeDao.save(assistantDoctorOffice);
        
        return true;
    }
}
