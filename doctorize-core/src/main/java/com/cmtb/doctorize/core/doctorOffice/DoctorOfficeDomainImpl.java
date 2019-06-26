/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmtb.doctorize.core.doctorOffice;

import com.cmtb.doctorize.data.doctor.AvailableDaysDao;
import com.cmtb.doctorize.data.doctor.DoctorOfficeDao;
import com.cmtb.doctorize.domain.doctor.AvailableDays;
import com.cmtb.doctorize.domain.doctor.DoctorOffice;
import com.cmtb.doctorize.domain.user.User;
import com.cmtb.doctorize.domain.user.UserDoctorOffice;
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
    
    @Resource(name = "AvailableDaysDao")
    private AvailableDaysDao availableDaysDao;
    
    @Resource(name = "UserDoctorOfficeDomain")
    private UserDoctorOfficeDomain userDoctorOfficeDomain;
    
    @Override
    public DoctorOffice save(DoctorOffice doctorOffice){
        
        Boolean isNew = (doctorOffice.getId() == null || doctorOffice.getId() <= 0);
        
        if(isNew){
            doctorOfficeDao.save(doctorOffice);
        }else{
            availableDaysDao.deleteAvailableDaysByDoctorOffice(doctorOffice.getId());
        }
        
        for (AvailableDays day : doctorOffice.getAvailableDays()) {
            DoctorOffice myOffice = new DoctorOffice();
            myOffice.setId(doctorOffice.getId());

            day.setOffice(myOffice);
            availableDaysDao.saveAvailableDay(day);
        }
        
        return doctorOffice;
    }
    
    @Override
    public List<DoctorOffice> getListByUserId(Long userId){
        return doctorOfficeDao.getListByUserId(userId);
    }
    
}
