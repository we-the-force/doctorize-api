/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmtb.doctorize.core.doctorOffice;

import com.cmtb.doctorize.domain.doctor.DoctorOffice;
import java.util.List;

/**
 *
 * @author pc
 */
public interface DoctorOfficeDomain {

    public List<DoctorOffice> getListByUserId(Long userId);

    public DoctorOffice save(DoctorOffice doctorOffice);
    
}
