/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmtb.doctorize.data.medicalAppointment;

import com.cmtb.doctorize.domain.medicalAppointment.MedicalAppointment;

/**
 *
 * @author pc
 */
public interface MedicalAppointmentDao {

    public MedicalAppointment save(MedicalAppointment medicalAppointment);
    
}
