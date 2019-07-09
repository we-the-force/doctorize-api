/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmtb.doctorize.core.medicalAppointment;

import com.cmtb.doctorize.domain.medicalAppointment.MedicalAppointmentDisplayObject;

/**
 *
 * @author pc
 */
public interface PatientNotifyNewAppointmentComponent {

    public void notify(MedicalAppointmentDisplayObject medicalAppointmentDO);
    
}
