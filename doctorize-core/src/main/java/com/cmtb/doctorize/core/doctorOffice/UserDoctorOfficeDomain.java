/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmtb.doctorize.core.doctorOffice;

import com.cmtb.doctorize.domain.doctor.UserDoctorOffice;

/**
 *
 * @author pc
 */
public interface UserDoctorOfficeDomain {

    public UserDoctorOffice save(UserDoctorOffice userDoctorOffice);
    
    public Boolean deleteByUserId(Long userId);
    
}
