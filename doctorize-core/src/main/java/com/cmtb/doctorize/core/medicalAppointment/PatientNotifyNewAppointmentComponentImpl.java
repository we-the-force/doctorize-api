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
import java.util.Calendar;
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
        
        Calendar cal = Calendar.getInstance();
        cal.setTime(medicalAppointmentDO.getDate());
        
        Integer year = cal.get(Calendar.YEAR);
        
        Integer monthInt = cal.get(Calendar.MONTH);
        String month = "";
        
        switch (monthInt) {
            case 0: {
                month = "Enero";
                break;
            }
            case 1: {
                month = "Febrero";
                break;
            }
            case 2: {
                month = "Marzo";
                break;
            }
            case 3: {
                month = "Abril";
                break;
            }
            case 4: {
                month = "Mayo";
                break;
            }
            case 5: {
                month = "Junio";
                break;
            }
            case 6: {
                month = "Julio";
                break;
            }
            case 7: {
                month = "Agosto";
                break;
            }
            case 8: {
                month = "Septiembre";
                break;
            }
            case 9: {
                month = "Octubre";
                break;
            }
            case 10: {
                month = "Noviembre";
                break;
            }
            case 11: {
                month = "Diciembre";
                break;
            }
            default:
                break;
                
        }
        
        Integer dayInt = cal.get(Calendar.DAY_OF_MONTH);
        String day = dayInt.toString().length() == 1? ("0" + dayInt.toString()): dayInt.toString();
        
        Integer hourInt = cal.get(Calendar.HOUR_OF_DAY);
        String hour = hourInt.toString().length() == 1? ("0" + hourInt.toString()): hourInt.toString();
        
        Integer minuteInt = cal.get(Calendar.MINUTE);
        String minute = minuteInt.toString().length() == 1? ("0" + minuteInt.toString()): minuteInt.toString();
        
        
        Email email = new Email();

        email.setTo(medicalAppointmentDO.getEmail());
        email.setEmailProcess(EmailProcessEnum.PATIENT_NEW_APPOINTMENT);

        Map<String, String> content = new HashMap<>();
        content.put("day", day);
        content.put("month", month);
        content.put("year", year.toString());
        content.put("time", hour + ":" + minute);
        
        email.setContent(content);

        emailUtils.send(email);
    }
}
