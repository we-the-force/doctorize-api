/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmtb.doctorize.core.assistent;

import com.cmtb.doctorize.data.assistant.AssistantDoctorOfficeDao;
import com.cmtb.doctorize.domain.assistant.AssistantDoctorOffice;
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
    public Boolean addUpdateAssistantOffice(AssistantDoctorOffice assistantDoctorOffice){
        if(assistantDoctorOfficeDao.exists(assistantDoctorOffice)){
            
        }
        
        return true;
    }
}
