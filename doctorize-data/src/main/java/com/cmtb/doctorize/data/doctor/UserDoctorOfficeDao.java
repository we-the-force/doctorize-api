/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmtb.doctorize.data.doctor;

import com.cmtb.doctorize.domain.doctor.UserDoctorOffice;
import java.util.List;

/**
 *
 * @author pc
 */
public interface UserDoctorOfficeDao {

    public UserDoctorOffice save(UserDoctorOffice userDoctorOffice);
    
    public Boolean deleteByUserId(Long userId);
    
    public List<UserDoctorOffice> getListAssistantsByDoctorId(Long doctorId);
    
}
