/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cmtb.doctorize.core.medicalAppointment;

import com.cmtb.doctorize.domain.medicalAppointment.MedicalAppointmentDisplayObject;
import com.cmtb.doctorize.domain.utilities.Email;
import com.cmtb.doctorize.domain.utilities.EmailProcessEnum;
import com.cmtb.doctorize.utilities.email.EmailUtilsComponent;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author pc
 */
@Component("PatientNotifyNewAppointmentComponent")
public class PatientNotifyNewAppointmentComponentImpl implements PatientNotifyNewAppointmentComponent {
    
    @Autowired
    private EmailUtilsComponent emailUtils;
    
    @Override
    public void notify(MedicalAppointmentDisplayObject medicalAppointmentDO) {
        Email email = new Email();

        email.setTo(medicalAppointmentDO.getEmail());
        email.setEmailProcess(EmailProcessEnum.PATIENT_NEW_APPOINTMENT);

        Map<String, String> content = new HashMap<>();
//        content.put("code", changePasswordDisplayObject.getCode());
//        content.put("user_name", changePasswordDisplayObject.getUser().getName());
//        content.put("user_email", changePasswordDisplayObject.getUser().getEmail());
//        content.put("url_confirm_account", URL_CONFIRM_PASS + changePasswordDisplayObject.getUser().getEmail() + URL_CONFIRM_PASS2 + changePasswordDisplayObject.getCode());
        
        email.setContent(content);

        emailUtils.send(email);
    }
}
