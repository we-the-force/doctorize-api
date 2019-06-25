/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmtb.doctorize.core.doctorOffice;

import com.cmtb.doctorize.domain.doctor.DoctorOffice;
import javax.annotation.Resource;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author pc
 */
@Component(value = "DoctorOfficeOrchestrator")
public class DoctorOfficeOrchestratorImpl implements DoctorOfficeOrchestrator {
    
    @Resource(name = "DoctorOfficeDomain")
    private DoctorOfficeDomain doctorOfficeDomain;
    
    @Transactional
    @Override
    public DoctorOffice save(DoctorOffice doctorOffice){
        return doctorOfficeDomain.save(doctorOffice);
    }
}
