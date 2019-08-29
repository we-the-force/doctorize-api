/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmtb.doctorize.core.doctorOffice;

import com.cmtb.doctorize.domain.doctor.DoctorOffice;
import com.cmtb.doctorize.domain.doctor.DoctorOfficeDisplayObject;
import java.util.List;
import java.util.Map;

/**
 *
 * @author pc
 */
public interface DoctorOfficeDomain {

    public List<DoctorOfficeDisplayObject> getListByUserId(Long userId);

    public DoctorOffice save(DoctorOffice doctorOffice);
    
    public DoctorOfficeDisplayObject getById(Long doctorOfficeId);
    
    public Boolean delete(Long doctorOfficeId);
    
    public boolean patch(Map<String, Object> doctorOfficeMap);
    
}
