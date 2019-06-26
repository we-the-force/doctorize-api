/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmtb.doctorize.core.doctorOffice;

import com.cmtb.doctorize.data.doctor.UserDoctorOfficeDao;
import com.cmtb.doctorize.domain.user.UserDoctorOffice;
import javax.annotation.Resource;
import org.springframework.stereotype.Component;

/**
 *
 * @author pc
 */
@Component(value = "UserDoctorOfficeDomain")
public class UserDoctorOfficeDomainImpl implements UserDoctorOfficeDomain {
    
    @Resource(name = "UserDoctorOfficeDao")
    private UserDoctorOfficeDao userDoctorOfficeDao;
    
    @Override
    public UserDoctorOffice save(UserDoctorOffice userDoctorOffice){
        return userDoctorOfficeDao.save(userDoctorOffice);
    }
}
