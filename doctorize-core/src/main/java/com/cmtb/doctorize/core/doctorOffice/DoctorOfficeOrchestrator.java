/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmtb.doctorize.core.doctorOffice;

import com.cmtb.doctorize.domain.doctor.DoctorOfficeDisplayObject;
import java.util.Map;

/**
 *
 * @author pc
 */
public interface DoctorOfficeOrchestrator {

    public DoctorOfficeDisplayObject save(DoctorOfficeDisplayObject doctorOfficeDisplayObject);
    
    public Boolean delete(Long doctorOfficeIf);
    
    public Boolean patch(Map<String, Object> doctorOfficeMap);
    
}
