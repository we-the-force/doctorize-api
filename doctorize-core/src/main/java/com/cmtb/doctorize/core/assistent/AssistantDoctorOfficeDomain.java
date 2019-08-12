/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmtb.doctorize.core.assistent;

import com.cmtb.doctorize.domain.assistant.AssistantDoctorOffice;
import com.cmtb.doctorize.domain.assistant.AssistantDoctorOfficeDisplayObject;
import java.util.List;

/**
 *
 * @author gealtec
 */
public interface AssistantDoctorOfficeDomain {

    public AssistantDoctorOffice save(AssistantDoctorOffice assistantDoctorOffice);
    
    public List<AssistantDoctorOffice> getListAssistantsByDoctorId(Long doctorId);
    
    public Boolean deleteAssistantByIdAndDoctor(Long assistantId, Long doctorId);
    
    public Boolean addUpdateAssistantOffice(AssistantDoctorOfficeDisplayObject assistantDoctorOfficeDO, Long assistantId);
}
