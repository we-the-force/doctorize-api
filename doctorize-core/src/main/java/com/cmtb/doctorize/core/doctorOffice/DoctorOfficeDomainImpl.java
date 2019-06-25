/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmtb.doctorize.core.doctorOffice;

import com.cmtb.doctorize.data.doctor.DoctorOfficeDao;
import com.cmtb.doctorize.domain.doctor.DoctorOffice;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Component;

/**
 *
 * @author pc
 */
@Component(value = "DoctorOfficeDomain")
public class DoctorOfficeDomainImpl implements DoctorOfficeDomain {
    
    @Resource(name = "DoctorOfficeDao")
    private DoctorOfficeDao doctorOfficeDao;
    
    @Override
    public DoctorOffice save(DoctorOffice doctorOffice){
        return doctorOfficeDao.save(doctorOffice);
    }
    
    @Override
    public List<DoctorOffice> getListByUserId(Long userId){
        return doctorOfficeDao.getListByUserId(userId);
    }
    
}
