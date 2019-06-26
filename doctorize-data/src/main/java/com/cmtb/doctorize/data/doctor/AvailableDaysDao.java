/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmtb.doctorize.data.doctor;

import com.cmtb.doctorize.domain.doctor.AvailableDays;

/**
 *
 * @author pc
 */
public interface AvailableDaysDao {

    public void deleteAvailableDaysByDoctorOffice(Long doctorOfficeId);

    public void saveAvailableDay(AvailableDays day);
    
}
